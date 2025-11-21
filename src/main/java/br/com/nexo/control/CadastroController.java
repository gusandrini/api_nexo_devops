package br.com.nexo.control;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.nexo.model.Funcao;
import br.com.nexo.model.EnumFuncao;
import br.com.nexo.model.Usuario;
import br.com.nexo.repository.FuncaoRepository;
import br.com.nexo.repository.UsuarioRepository;
import br.com.nexo.service.UsuarioCachingService;
import br.com.nexo.mensageria.RabbitMQProdutor;

@Controller
public class CadastroController {
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

    @GetMapping("/cadastro")
    public ModelAndView cadastroForm() {
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastrarUsuario(@Validated(br.com.nexo.validation.ValidationGroups.Create.class) Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("cadastro");
            mv.addObject("usuario", usuario);
            return mv;
        }
        usuario.setNmSenha(encoder.encode(usuario.getNmSenha()));
        // Sempre atribui função CLIENTE
        Set<Funcao> funcoes = new HashSet<>();
        Funcao funcaoCliente = repFuncao.findAll().stream()
            .filter(f -> f.getNmFuncao() == EnumFuncao.CLIENTE)
            .findFirst()
            .orElse(null);
        if (funcaoCliente != null) {
            funcoes.add(funcaoCliente);
        }
        usuario.setFuncoes(funcoes);
        repUsuario.save(usuario);
        cache.limparCache();
        String notificacao = "Novo cadastro: " + usuario.getNmCliente() + " (" + usuario.getNmEmail() + ")";
        rabbitMQProdutor.enviarMensagem(notificacao);
        return new ModelAndView("redirect:/login?cadastroSucesso");
    }
}
