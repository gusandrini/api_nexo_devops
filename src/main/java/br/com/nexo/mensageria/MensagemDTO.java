package br.com.nexo.mensageria;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

public class MensagemDTO implements Serializable {
    @NotBlank
    private String conteudo;

    public MensagemDTO() {}

    public MensagemDTO(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
