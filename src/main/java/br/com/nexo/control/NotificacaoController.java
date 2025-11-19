package br.com.nexo.control;

import br.com.nexo.mensageria.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/notificacoes")
    public String listarNotificacoes(Model model) {
        model.addAttribute("notificacoes", notificacaoService.listar());
        return "mensageria/notificacoes";
    }
}
