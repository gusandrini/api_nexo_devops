package br.com.nexo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição de login do usuário")
public class LoginRequest {
    @Schema(description = "E-mail do usuário", example = "nome@email.com")
    private String nmEmail;
    @Schema(description = "Senha do usuário", example = "111111")
    private String nmSenha;

    public String getNmEmail() {
        return nmEmail;
    }
    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }
    public String getNmSenha() {
        return nmSenha;
    }
    public void setNmSenha(String nmSenha) {
        this.nmSenha = nmSenha;
    }
}
