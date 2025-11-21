package br.com.nexo.control;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.nexo.model.Funcao;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.FuncaoRepository;
import br.com.nexo.repository.UsuarioRepository;
import br.com.nexo.service.UsuarioCachingService;

import br.com.nexo.mensageria.RabbitMQProdutor;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository repUsuario;

    @Autowired
    private FuncaoRepository repFuncao;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioCachingService cache;

    @Autowired
    private RabbitMQProdutor rabbitMQProdutor;

    @GetMapping("/usuario/lista")
    public ModelAndView listarUsuarios() {
        ModelAndView mv = new ModelAndView("usuario/lista");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Usuario> op = repUsuario.findByNmEmail(auth.getName());
        op.ifPresent(u -> mv.addObject("usuario", u));
        mv.addObject("usuarios", repUsuario.findAll());
        mv.addObject("lista_funcoes", repFuncao.findAll());
        return mv;
    }

    @GetMapping("/usuario/novo")
    public ModelAndView novoUsuario() {
        ModelAndView mv = new ModelAndView("usuario/novo");
        mv.addObject("usuario", new Usuario());
        mv.addObject("lista_funcoes", repFuncao.findAll());
        return mv;
    }

    @PostMapping("/usuario/novo")
    public ModelAndView inserirUsuario(@Validated(br.com.nexo.validation.ValidationGroups.Create.class) Usuario usuario, BindingResult bindingResult,
            @RequestParam(name = "id_funcao", required = false) Long[] id_funcao) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/novo");
            mv.addObject("usuario", usuario);
            mv.addObject("lista_funcoes", repFuncao.findAll());
            return mv;
        }
        usuario.setNmSenha(encoder.encode(usuario.getNmSenha()));
        Set<Funcao> funcoes = new HashSet<>();
        if (id_funcao != null) {
            for (Long id : id_funcao) {
                repFuncao.findById(id).ifPresent(funcoes::add);
            }
        }
        usuario.setFuncoes(funcoes);
        repUsuario.save(usuario);
        cache.limparCache();
        String notificacao = "Usuário cadastrado com sucesso: " + usuario.getNmCliente() + " (" + usuario.getNmEmail() + ")";
        rabbitMQProdutor.enviarMensagem(notificacao);
        return new ModelAndView("redirect:/usuario/lista");
    }

    @GetMapping("/usuario/editar")
    public ModelAndView editarUsuarioForm(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("usuario/editar");
        Usuario usuario = repUsuario.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        mv.addObject("usuario", usuario);
        mv.addObject("lista_funcoes", repFuncao.findAll());
        return mv;
    }

    @PostMapping("/usuario/editar")
    public ModelAndView editarUsuario(@Validated(br.com.nexo.validation.ValidationGroups.Update.class) Usuario usuario, BindingResult bindingResult,
            @RequestParam(name = "id_funcao", required = false) Long[] id_funcao) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("usuario/editar");
            mv.addObject("usuario", usuario);
            mv.addObject("lista_funcoes", repFuncao.findAll());
            return mv;
        }

        if (usuario.getNmSenha() == null || usuario.getNmSenha().isBlank()) {
            repUsuario.findById(usuario.getIdUsuario())
                    .ifPresent(existing -> usuario.setNmSenha(existing.getNmSenha()));
        } else {
            usuario.setNmSenha(encoder.encode(usuario.getNmSenha()));
        }

        Set<Funcao> funcoes = new HashSet<>();
        if (id_funcao != null) {
            for (Long id : id_funcao) {
                repFuncao.findById(id).ifPresent(funcoes::add);
            }
        }
        usuario.setFuncoes(funcoes);
        repUsuario.save(usuario);
        cache.limparCache();
        String notificacao = "Usuário editado: " + usuario.getNmCliente() + " (" + usuario.getNmEmail() + ")";
        rabbitMQProdutor.enviarMensagem(notificacao);
        return new ModelAndView("redirect:/usuario/lista");
    }

    @GetMapping("/usuario/excluir/{id}")
    public ModelAndView removerUsuarioExistente(@PathVariable Long id) {
        repUsuario.deleteById(id);
        cache.limparCache();
        return new ModelAndView("redirect:/usuario/lista");
    }
}
