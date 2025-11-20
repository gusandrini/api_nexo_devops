
    
package br.com.nexo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.core.Authentication;
import br.com.nexo.dto.DescricaoClienteDTO;
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
    public Double predizer(@RequestBody DescricaoClienteDTO dto, Authentication authentication) {
        return predicaoService.obterPercentualMudanca(dto);
    }

}
