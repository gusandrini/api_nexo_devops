package br.com.nexo.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import br.com.nexo.dto.PredicaoDTO;
import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.Predicao;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.PredicaoRepository;

@RestController
@RequestMapping("/predicoes")
public class PredicaoApiController {

    @Autowired
    private PredicaoRepository repPred;

    @Autowired
    private DescricaoClienteRepository repDesc;

    @GetMapping("/todos")
    public List<Predicao> todos() {
        return repPred.findAll();
    }

    @GetMapping("/{id}")
    public Predicao porId(@PathVariable Long id) {
        return repPred.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/inserir")
    public Predicao inserir(@Valid @RequestBody PredicaoDTO dto) {
        Predicao p = new Predicao();
        if (dto.getIdDescricao() != null) {
            DescricaoCliente dc = repDesc.findById(dto.getIdDescricao())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição não encontrada"));
            p.setDescricaoCliente(dc);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idDescricao é obrigatório");
        }
        p.setDsResultadoPredicao(dto.getDsResultadoPredicao());
        p.setDsRecomendacao(dto.getDsRecomendacao());
        repPred.save(p);
        return p;
    }

    @PutMapping("/atualizar/{id}")
    public Predicao atualizar(@Valid @RequestBody PredicaoDTO dto, @PathVariable Long id) {
        Optional<Predicao> op = repPred.findById(id);
        if (op.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Predicao p = op.get();
        if (dto.getIdDescricao() != null) {
            DescricaoCliente dc = repDesc.findById(dto.getIdDescricao())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Descrição não encontrada"));
            p.setDescricaoCliente(dc);
        }
        p.setDsResultadoPredicao(dto.getDsResultadoPredicao());
        p.setDsRecomendacao(dto.getDsRecomendacao());
        repPred.save(p);
        return p;
    }

    @DeleteMapping("/remover/{id}")
    public Predicao remover(@PathVariable Long id) {
        Predicao p = repPred.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        repPred.delete(p);
        return p;
    }
}
