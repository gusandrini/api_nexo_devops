package br.com.nexo.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.nexo.model.Usuario;
import br.com.nexo.repository.UsuarioRepository;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository repUsuario;

    @GetMapping({"/", "/home"})
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
            Optional<Usuario> op = repUsuario.findByNmEmail(auth.getName());
            op.ifPresent(u -> mv.addObject("usuario", u));
        }
        return mv;
    }
}
