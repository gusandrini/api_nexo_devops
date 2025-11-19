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
import br.com.nexo.model.CampoEstudo;
import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.InfluenciaFamiliar;
import br.com.nexo.model.NivelEducacional;
import br.com.nexo.model.Ocupacao;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.OcupacaoRepository;
import br.com.nexo.repository.UsuarioRepository;
import br.com.nexo.repository.CampoEstudoRepository;
import br.com.nexo.repository.NivelEducacionalRepository;
import br.com.nexo.repository.InfluenciaFamiliarRepository;


@RestController
@RequestMapping("/descricao-clientes")
public class DescricaoClienteApiController {

    @Autowired
    private DescricaoClienteRepository repDesc;

    @Autowired
    private UsuarioRepository repUsuario;

    @Autowired
    private OcupacaoRepository repOcupacao;
    
    @Autowired
    private CampoEstudoRepository repCampoEstudo;
    
    @Autowired
    private NivelEducacionalRepository repNivelEducacional;

    @Autowired
    private InfluenciaFamiliarRepository repInfluenciaFamiliar;

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
        
        if (dto.getIdOcupacao() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idOcupacao é obrigatório");
        }
        if (dto.getIdCampoEstudo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idCampoEstudo é obrigatório");
        }       
        if (dto.getIdNivelEducacional() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idNivelEducacional é obrigatório");
        }
        if (dto.getIdInfluenciaFamiliar() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idInfluenciaFamiliar é obrigatório");
        }
        Ocupacao ocup = repOcupacao.findById(dto.getIdOcupacao())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocupação não encontrada"));
        desc.setOcupacao(ocup);
        CampoEstudo campo = repCampoEstudo.findById(dto.getIdCampoEstudo())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campo de estudo não encontrado"));
        desc.setCampoEstudo(campo);
        
        NivelEducacional nivel = repNivelEducacional.findById(dto.getIdNivelEducacional())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nível educacional não encontrado"));
        desc.setNivelEducacional(nivel);
        InfluenciaFamiliar influencia = repInfluenciaFamiliar.findById(dto.getIdInfluenciaFamiliar())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Influência familiar não encontrada"));
        desc.setInfluenciaFamiliar(influencia);
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
        // só atualiza se for fornecido
        if (dto.getIdOcupacao() != null) {
            Ocupacao ocup = repOcupacao.findById(dto.getIdOcupacao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocupação não encontrada"));
            desc.setOcupacao(ocup);
        }
        if (dto.getIdCampoEstudo() != null) {
            CampoEstudo campo = repCampoEstudo.findById(dto.getIdCampoEstudo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Campo de estudo não encontrado"));
            desc.setCampoEstudo(campo);
        }        
        if (dto.getIdNivelEducacional() != null) {
            NivelEducacional nivel = repNivelEducacional.findById(dto.getIdNivelEducacional())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nível educacional não encontrado"));
            desc.setNivelEducacional(nivel);
        }
        if (dto.getIdInfluenciaFamiliar() != null) {
            InfluenciaFamiliar influencia = repInfluenciaFamiliar.findById(dto.getIdInfluenciaFamiliar())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Influência familiar não encontrada"));
            desc.setInfluenciaFamiliar(influencia);
        }
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
