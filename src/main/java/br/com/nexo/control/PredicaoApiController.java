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

    @GetMapping("/todos")
    public List<Predicao> todos() {
        return repPred.findAll();
    }

    @GetMapping("/{id}")
    public Predicao porId(@PathVariable Long id) {
        return repPred.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
