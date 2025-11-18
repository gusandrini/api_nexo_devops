package br.com.nexo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.nexo.model.DescricaoCliente;
import br.com.nexo.model.Predicao;
import br.com.nexo.repository.DescricaoClienteRepository;
import br.com.nexo.repository.PredicaoRepository;

import jakarta.validation.Valid;

@Controller
public class PredicaoController {

    @Autowired
    private PredicaoRepository repPred;

    @Autowired
    private DescricaoClienteRepository repDesc;

    @GetMapping("/predicoes/lista")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("predicao/lista");
        mv.addObject("predicoes", repPred.findAll());
        mv.addObject("descricao_list", repDesc.findAll());
        return mv;
    }

    @GetMapping("/predicoes/novo")
    public ModelAndView novoForm() {
        ModelAndView mv = new ModelAndView("predicao/novo");
        mv.addObject("predicao", new Predicao());
        mv.addObject("descricao_list", repDesc.findAll());
        return mv;
    }

    @PostMapping("/predicoes/novo")
    public ModelAndView inserir(@Valid Predicao predicao, BindingResult bindingResult,
            @RequestParam(name = "idDescricao", required = false) Long idDescricao) {

        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("predicao/novo");
            mv.addObject("predicao", predicao);
            mv.addObject("descricao_list", repDesc.findAll());
            return mv;
        }
        if (idDescricao != null) {
            repDesc.findById(idDescricao).ifPresent(predicao::setDescricaoCliente);
        }
        repPred.save(predicao);
        return new ModelAndView("redirect:/predicoes/lista");
    }

    @GetMapping("/predicoes/editar")
    public ModelAndView editarForm(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("predicao/editar");
        Predicao p = repPred.findById(id).orElseThrow(() -> new IllegalArgumentException("Predição não encontrada"));
        mv.addObject("predicao", p);
        mv.addObject("descricao_list", repDesc.findAll());
        return mv;
    }

    @PostMapping("/predicoes/editar")
    public ModelAndView editar(@Valid Predicao predicao, BindingResult bindingResult,
            @RequestParam(name = "idDescricao", required = false) Long idDescricao) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("predicao/editar");
            mv.addObject("predicao", predicao);
            mv.addObject("descricao_list", repDesc.findAll());
            return mv;
        }
        if (idDescricao != null) {
            repDesc.findById(idDescricao).ifPresent(predicao::setDescricaoCliente);
        } else {
            repPred.findById(predicao.getIdPredicao()).ifPresent(existing -> predicao.setDescricaoCliente(existing.getDescricaoCliente()));
        }
        repPred.save(predicao);
        return new ModelAndView("redirect:/predicoes/lista");
    }

    @GetMapping("/predicoes/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        repPred.deleteById(id);
        return new ModelAndView("redirect:/predicoes/lista");
    }
}
