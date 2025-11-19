package br.com.nexo.control;

import br.com.nexo.mensageria.MensagemDTO;
import br.com.nexo.mensageria.RabbitMQProdutor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MensageriaController {

    @Autowired
    private RabbitMQProdutor produtor;

    @GetMapping("/mensageria/enviar")
    public String exibirFormularioEnvio(Model model) {
        model.addAttribute("mensagemDTO", new MensagemDTO());
        return "mensageria/enviar";
    }

    @PostMapping("/mensageria/enviar")
    public String enviarMensagem(@Valid @ModelAttribute("mensagemDTO") MensagemDTO mensagemDTO,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "mensageria/enviar";
        }
        produtor.enviarMensagem(mensagemDTO.getConteudo());
        return "redirect:/mensageria/confirmacao";
    }

    @GetMapping("/mensageria/confirmacao")
    public String confirmacaoEnvio() {
        return "mensageria/confirmacao";
    }
}
