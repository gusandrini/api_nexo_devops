package br.com.nexo.control;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.nexo.model.CampoEstudo;
import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.Genero;
import br.com.nexo.model.InfluenciaFamiliar;
import br.com.nexo.model.NivelEducacional;
import br.com.nexo.model.Ocupacao;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.CampoEstudoRepository;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.GeneroRepository;
import br.com.nexo.repository.InfluenciaFamiliarRepository;
import br.com.nexo.repository.NivelEducacionalRepository;
import br.com.nexo.repository.OcupacaoRepository;
import br.com.nexo.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
public class DescricaoClienteController {

    @Autowired
    private DescricaoClienteRepository repDesc;

    @Autowired
    private UsuarioRepository repUsuario;

    @Autowired
    private OcupacaoRepository repOcupacao;

    @Autowired
    private CampoEstudoRepository repCampoEstudo;

    @Autowired
    private GeneroRepository repGenero;

    @Autowired
    private NivelEducacionalRepository repNivelEducacional;

    @Autowired
    private InfluenciaFamiliarRepository repInfluenciaFamiliar;

    @GetMapping("/descricao-clientes/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("descricao_cliente/lista");
        mv.addObject("descricaoClientes", repDesc.findAll());
        mv.addObject("lista_ocupacoes", repOcupacao.findAll());
        mv.addObject("lista_campos", repCampoEstudo.findAll());
        mv.addObject("lista_generos", repGenero.findAll());
        mv.addObject("lista_niveis", repNivelEducacional.findAll());
        mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
        mv.addObject("lista_usuarios", repUsuario.findAll());
        return mv;
    }

    @GetMapping("/descricao-clientes/novo")
    public ModelAndView novoForm() {
        ModelAndView mv = new ModelAndView("descricao_cliente/novo");
        mv.addObject("descricaoCliente", new DescricaoCliente());
        mv.addObject("lista_ocupacoes", repOcupacao.findAll());
        mv.addObject("lista_campos", repCampoEstudo.findAll());
        mv.addObject("lista_generos", repGenero.findAll());
        mv.addObject("lista_niveis", repNivelEducacional.findAll());
        mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
        mv.addObject("lista_usuarios", repUsuario.findAll());
        return mv;
    }

    @PostMapping("/descricao-clientes/novo")
    public ModelAndView inserir(@Valid DescricaoCliente descricaoCliente, BindingResult bindingResult,
            @RequestParam(name = "idUsuario", required = false) Long idUsuario,
            @RequestParam(name = "idOcupacao", required = false) Long idOcupacao,
            @RequestParam(name = "idCampoEstudo", required = false) Long idCampoEstudo,
            @RequestParam(name = "idGenero", required = false) Long idGenero,
            @RequestParam(name = "idNivelEducacional", required = false) Long idNivelEducacional,
            @RequestParam(name = "idInfluenciaFamiliar", required = false) Long idInfluenciaFamiliar) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("descricao_cliente/novo");
            mv.addObject("descricaoCliente", descricaoCliente);
            mv.addObject("lista_ocupacoes", repOcupacao.findAll());
            mv.addObject("lista_campos", repCampoEstudo.findAll());
            mv.addObject("lista_generos", repGenero.findAll());
            mv.addObject("lista_niveis", repNivelEducacional.findAll());
            mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
            mv.addObject("lista_usuarios", repUsuario.findAll());
            return mv;
        }

        if (idUsuario != null) {
            repUsuario.findById(idUsuario).ifPresent(descricaoCliente::setUsuario);
        }
        if (idOcupacao != null) {
            repOcupacao.findById(idOcupacao).ifPresent(descricaoCliente::setOcupacao);
        }
        if (idCampoEstudo != null) {
            repCampoEstudo.findById(idCampoEstudo).ifPresent(descricaoCliente::setCampoEstudo);
        }
        if (idGenero != null) {
            repGenero.findById(idGenero).ifPresent(descricaoCliente::setGenero);
        }
        if (idNivelEducacional != null) {
            repNivelEducacional.findById(idNivelEducacional).ifPresent(descricaoCliente::setNivelEducacional);
        }
        if (idInfluenciaFamiliar != null) {
            repInfluenciaFamiliar.findById(idInfluenciaFamiliar).ifPresent(descricaoCliente::setInfluenciaFamiliar);
        }
        descricaoCliente.setDtInput(descricaoCliente.getDtInput() == null ? LocalDateTime.now() : descricaoCliente.getDtInput());
        repDesc.save(descricaoCliente);
        return new ModelAndView("redirect:/descricao-clientes/lista");
    }

    @GetMapping("/descricao-clientes/editar")
    public ModelAndView editarForm(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("descricao_cliente/editar");
        DescricaoCliente desc = repDesc.findById(id).orElseThrow(() -> new IllegalArgumentException("Descrição não encontrada"));
        mv.addObject("descricaoCliente", desc);
        mv.addObject("lista_ocupacoes", repOcupacao.findAll());
        mv.addObject("lista_campos", repCampoEstudo.findAll());
        mv.addObject("lista_generos", repGenero.findAll());
        mv.addObject("lista_niveis", repNivelEducacional.findAll());
        mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
        mv.addObject("lista_usuarios", repUsuario.findAll());
        return mv;
    }

    @PostMapping("/descricao-clientes/editar")
    public ModelAndView editar(@Valid DescricaoCliente descricaoCliente, BindingResult bindingResult,
            @RequestParam(name = "idUsuario", required = false) Long idUsuario,
            @RequestParam(name = "idOcupacao", required = false) Long idOcupacao,
            @RequestParam(name = "idCampoEstudo", required = false) Long idCampoEstudo,
            @RequestParam(name = "idGenero", required = false) Long idGenero,
            @RequestParam(name = "idNivelEducacional", required = false) Long idNivelEducacional,
            @RequestParam(name = "idInfluenciaFamiliar", required = false) Long idInfluenciaFamiliar) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("descricao_cliente/editar");
            mv.addObject("descricaoCliente", descricaoCliente);
            mv.addObject("lista_ocupacoes", repOcupacao.findAll());
            mv.addObject("lista_campos", repCampoEstudo.findAll());
            mv.addObject("lista_generos", repGenero.findAll());
            mv.addObject("lista_niveis", repNivelEducacional.findAll());
            mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
            mv.addObject("lista_usuarios", repUsuario.findAll());
            return mv;
        }

        // Set associations if provided; otherwise keep current values
        if (idUsuario != null) {
            repUsuario.findById(idUsuario).ifPresent(descricaoCliente::setUsuario);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setUsuario(existing.getUsuario()));
        }
        if (idOcupacao != null) {
            repOcupacao.findById(idOcupacao).ifPresent(descricaoCliente::setOcupacao);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setOcupacao(existing.getOcupacao()));
        }
        if (idCampoEstudo != null) {
            repCampoEstudo.findById(idCampoEstudo).ifPresent(descricaoCliente::setCampoEstudo);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setCampoEstudo(existing.getCampoEstudo()));
        }
        if (idGenero != null) {
            repGenero.findById(idGenero).ifPresent(descricaoCliente::setGenero);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setGenero(existing.getGenero()));
        }
        if (idNivelEducacional != null) {
            repNivelEducacional.findById(idNivelEducacional).ifPresent(descricaoCliente::setNivelEducacional);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setNivelEducacional(existing.getNivelEducacional()));
        }
        if (idInfluenciaFamiliar != null) {
            repInfluenciaFamiliar.findById(idInfluenciaFamiliar).ifPresent(descricaoCliente::setInfluenciaFamiliar);
        } else {
            repDesc.findById(descricaoCliente.getIdDescricao()).ifPresent(existing -> descricaoCliente.setInfluenciaFamiliar(existing.getInfluenciaFamiliar()));
        }

        repDesc.save(descricaoCliente);
        return new ModelAndView("redirect:/descricao-clientes/lista");
    }

    @GetMapping("/descricao-clientes/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        repDesc.deleteById(id);
        return new ModelAndView("redirect:/descricao-clientes/lista");
    }

}
