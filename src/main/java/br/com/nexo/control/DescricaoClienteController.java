package br.com.nexo.control;
    
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.nexo.model.Predicao;
import br.com.nexo.repository.PredicaoRepository;
import br.com.nexo.service.PredicaoService;

import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.dto.DescricaoClienteDTO;
import br.com.nexo.repository.CampoEstudoRepository;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.InfluenciaFamiliarRepository;
import br.com.nexo.repository.NivelEducacionalRepository;
import br.com.nexo.repository.OcupacaoRepository;
import br.com.nexo.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Controller
public class DescricaoClienteController {
	
    @Autowired
    private PredicaoService predicaoService;

    @Autowired
    private PredicaoRepository repPredicao;

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

    @GetMapping("/descricao-clientes/novo")
    public ModelAndView novoForm() {
        ModelAndView mv = new ModelAndView("descricao_cliente/novo");
        mv.addObject("descricaoCliente", new DescricaoClienteDTO());
        mv.addObject("percentual", null);
        addListasAtributos(mv);
        return mv;
    }

    @PostMapping("/descricao-clientes/novo")
    public ModelAndView inserir(@Valid DescricaoClienteDTO descricaoCliente, BindingResult bindingResult,
        @RequestParam(name = "idOcupacao", required = false) Long idOcupacao,
        @RequestParam(name = "idCampoEstudo", required = false) Long idCampoEstudo,
        @RequestParam(name = "idNivelEducacional", required = false) Long idNivelEducacional,
        @RequestParam(name = "idInfluenciaFamiliar", required = false) Long idInfluenciaFamiliar) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("descricao_cliente/novo");
            mv.addObject("descricaoCliente", descricaoCliente);
            mv.addObject("percentual", null);
            addListasAtributos(mv);
            return mv;
        }

        DescricaoCliente entity = new DescricaoCliente();
        entity.setNrIdade(descricaoCliente.getNrIdade());
        entity.setNrSalario(descricaoCliente.getNrSalario());
        entity.setQtdaAnosExperiencia(descricaoCliente.getQtdaAnosExperiencia());
        entity.setDsSatisfacao(descricaoCliente.getDsSatisfacao());
        entity.setDsTecnologia(descricaoCliente.getDsTecnologia());
        entity.setDsMudanca(descricaoCliente.getDsMudanca());
        entity.setDtInput(descricaoCliente.getDtInput() == null ? java.time.LocalDateTime.now() : descricaoCliente.getDtInput());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        br.com.nexo.model.Usuario usuario = repUsuario.findByNmEmail(email).orElse(null);
        entity.setUsuario(usuario);

        if (idOcupacao != null) repOcupacao.findById(idOcupacao).ifPresent(entity::setOcupacao);
        if (idCampoEstudo != null) repCampoEstudo.findById(idCampoEstudo).ifPresent(entity::setCampoEstudo);
        if (idNivelEducacional != null) repNivelEducacional.findById(idNivelEducacional).ifPresent(entity::setNivelEducacional);
        if (idInfluenciaFamiliar != null) repInfluenciaFamiliar.findById(idInfluenciaFamiliar).ifPresent(entity::setInfluenciaFamiliar);
        DescricaoCliente saved = repDesc.save(entity);

        DescricaoClienteDTO dto = new DescricaoClienteDTO();
        dto.setIdOcupacao(saved.getOcupacao() != null ? saved.getOcupacao().getIdOcupacao() : null);
        dto.setIdCampoEstudo(saved.getCampoEstudo() != null ? saved.getCampoEstudo().getIdCampoEstudo() : null);
        dto.setIdNivelEducacional(saved.getNivelEducacional() != null ? saved.getNivelEducacional().getIdNivelEducacional() : null);
        dto.setDsSatisfacao(saved.getDsSatisfacao());
        dto.setNrSalario(saved.getNrSalario());
        dto.setNrIdade(saved.getNrIdade());

        Map<String, Object> iaResult = predicaoService.obterPredicaoIA(dto);
        ModelAndView mv = new ModelAndView("descricao_cliente/novo");
        if (iaResult != null && iaResult.containsKey("probabilidade_mudar")) {
            Double percentual = null;
            Integer classePrevista = null;
            try {
                percentual = Double.valueOf(iaResult.get("probabilidade_mudar").toString()) * 100;
                if (iaResult.get("classe_prevista") != null) {
                    classePrevista = Integer.valueOf(iaResult.get("classe_prevista").toString());
                }
            } catch (Exception e) { percentual = null; classePrevista = null; }
            Predicao pred = new Predicao();
            pred.setDescricaoCliente(saved);
            pred.setDsResultadoPredicao(percentual != null ? percentual.intValue() : null);
            pred.setDsRecomendacao("Predição automática IA");
            repPredicao.save(pred);
            mv.addObject("percentual", percentual);
            mv.addObject("classePrevista", classePrevista);
        } else {
            mv.addObject("erroPredicao", "Não foi possível obter a predição da IA. Tente novamente mais tarde.");
        }
        mv.addObject("descricaoCliente", descricaoCliente);
        addListasAtributos(mv);
        return mv;

    }

    private void addListasAtributos(ModelAndView mv) {
        mv.addObject("lista_ocupacoes", repOcupacao.findAll());
        mv.addObject("lista_campos", repCampoEstudo.findAll());
        mv.addObject("lista_niveis", repNivelEducacional.findAll());
        mv.addObject("lista_influencias", repInfluenciaFamiliar.findAll());
    }

    @GetMapping("/descricao-clientes/historico")
    public ModelAndView historicoFormularios() {
        ModelAndView mv = new ModelAndView("descricao_cliente/historico");
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = auth != null ? auth.getName() : null;
        java.util.List<DescricaoCliente> descricoes;
        if (email != null && !email.isBlank()) {
            descricoes = repDesc.findByUsuarioNmEmailIgnoreCase(email);
        } else {
            descricoes = java.util.Collections.emptyList();
        }
        java.util.Map<Long, Predicao> predicoes = new java.util.HashMap<>();
        for (DescricaoCliente d : descricoes) {
            Predicao p = repPredicao.findFirstByDescricaoClienteOrderByIdPredicaoDesc(d);
            if (p != null) {
                predicoes.put(d.getIdDescricao(), p);
            }
        }
        mv.addObject("descricoes", descricoes);
        mv.addObject("predicoes", predicoes);
        return mv;
    }

}
