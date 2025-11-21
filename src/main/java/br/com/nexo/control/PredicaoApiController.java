
    
package br.com.nexo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.core.Authentication;
import br.com.nexo.dto.DescricaoClienteDTO;
import br.com.nexo.dto.PredicaoIADTO;
import br.com.nexo.service.PredicaoService;
import br.com.nexo.model.Predicao;
import br.com.nexo.repository.PredicaoRepository;

@RestController
@RequestMapping("/api/predicoes")
public class PredicaoApiController {

    @Autowired
    private PredicaoRepository repPred;

    @GetMapping("/todos")
    public List<Predicao> todos() {
        return repPred.findAll();
    }

    @GetMapping("/{id}")
    public Predicao porId(@PathVariable Long id) {
        return repPred.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Autowired
    private PredicaoService predicaoService;
    @PostMapping("/predizer")
    public PredicaoIADTO predizer(@RequestBody DescricaoClienteDTO dto, Authentication authentication) {
        var iaResult = predicaoService.obterPredicaoIA(dto);
        if (iaResult == null) return new PredicaoIADTO(null, null);
        Integer classePrevista = null;
        Double probabilidadeMudar = null;
        try {
            if (iaResult.get("classe_prevista") != null) {
                classePrevista = Integer.valueOf(iaResult.get("classe_prevista").toString());
            }
            if (iaResult.get("probabilidade_mudar") != null) {
                probabilidadeMudar = Double.valueOf(iaResult.get("probabilidade_mudar").toString());
            }
        } catch (Exception e) {
            // erro de parse, retorna null
        }
        return new PredicaoIADTO(classePrevista, probabilidadeMudar);
    }

}
