package br.com.nexo.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MensageriaController {

    @GetMapping("/mensageria/enviar")
    public String exibirFormularioEnvio(Model model) {
        // Adiciona um atributo vazio só para evitar erros na view
        model.addAttribute("conteudo", "");
        return "mensageria/enviar";
    }

    @PostMapping("/mensageria/enviar")
    public String enviarMensagem(String conteudo, Model model) {

        // Aqui não envia para RabbitMQ (porque foi removido)
        // Você pode registrar no log, salvar no banco, etc. se quiser
        System.out.println("Mensagem enviada (simulado): " + conteudo);

        return "redirect:/mensageria/confirmacao";
    }

    @GetMapping("/mensageria/confirmacao")
    public String confirmacaoEnvio() {
        return "mensageria/confirmacao";
    }
}
