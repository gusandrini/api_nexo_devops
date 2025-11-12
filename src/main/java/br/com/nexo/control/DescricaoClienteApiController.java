package br.com.nexo.control;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import br.com.nexo.dto.DescricaoClienteDTO;
import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.UsuarioRepository;

@RestController
@RequestMapping("/descricao-clientes")
public class DescricaoClienteApiController {

    @Autowired
    private DescricaoClienteRepository repDesc;

    @Autowired
    private UsuarioRepository repUsuario;

    @GetMapping("/todos")
    public List<DescricaoCliente> retornaTodos() {
        return repDesc.findAll();
    }

    @GetMapping("/{id_descricao}")
    public DescricaoCliente retornaPorId(@PathVariable Long id_descricao) {
        Optional<DescricaoCliente> op = repDesc.findById(id_descricao);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/inserir")
    public DescricaoCliente inserir(@Valid @RequestBody DescricaoClienteDTO dto) {
        DescricaoCliente desc = new DescricaoCliente();
        if (dto.getIdUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idUsuario é obrigatório");
        }
        Usuario usuario = repUsuario.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        desc.setUsuario(usuario);
        desc.setNmArea(dto.getNmArea());
        desc.setDsOcupacao(dto.getDsOcupacao());
        desc.setQtdaAnosExperiencia(dto.getQtdaAnosExperiencia());
        desc.setDsSatisfacao(dto.getDsSatisfacao());
        desc.setDsTecnologia(dto.getDsTecnologia());
        desc.setDsMudanca(dto.getDsMudanca());
        desc.setDtInput(dto.getDtInput() == null ? LocalDateTime.now() : dto.getDtInput());
        repDesc.save(desc);
        return desc;
    }

    @PutMapping("/atualizar/{id_descricao}")
    public DescricaoCliente atualizar(@Valid @RequestBody DescricaoClienteDTO dto, @PathVariable Long id_descricao) {
        Optional<DescricaoCliente> op = repDesc.findById(id_descricao);
        if (op.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        DescricaoCliente desc = op.get();
        if (dto.getIdUsuario() != null) {
            Usuario usuario = repUsuario.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
            desc.setUsuario(usuario);
        }
        desc.setNmArea(dto.getNmArea());
        desc.setDsOcupacao(dto.getDsOcupacao());
        desc.setQtdaAnosExperiencia(dto.getQtdaAnosExperiencia());
        desc.setDsSatisfacao(dto.getDsSatisfacao());
        desc.setDsTecnologia(dto.getDsTecnologia());
        desc.setDsMudanca(dto.getDsMudanca());
        if (dto.getDtInput() != null) {
            desc.setDtInput(dto.getDtInput());
        }
        repDesc.save(desc);
        return desc;
    }

    @DeleteMapping("/remover/{id_descricao}")
    public DescricaoCliente remover(@PathVariable Long id_descricao) {
        Optional<DescricaoCliente> op = repDesc.findById(id_descricao);
        if (op.isPresent()) {
            DescricaoCliente desc = op.get();
            repDesc.delete(desc);
            return desc;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
