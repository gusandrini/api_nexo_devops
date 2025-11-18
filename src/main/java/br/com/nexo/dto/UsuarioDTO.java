package br.com.nexo.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.com.nexo.model.Usuario;
import java.util.stream.Collectors;


public class UsuarioDTO{

    private Long idUsuario;

    @NotBlank
    @Size(max = 80)
    private String nmCliente;

    @NotBlank
    @Email
    @Size(max = 80)
    private String nmEmail;

    @NotBlank
    @Size(max = 100)
    private String nmSenha;

    private Set<FuncaoDTO> funcoes = new HashSet<>();

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        if (usuario != null) {
            this.idUsuario = usuario.getIdUsuario();
            this.nmCliente = usuario.getNmCliente();
            this.nmEmail = usuario.getNmEmail();
            this.nmSenha = usuario.getNmSenha();
            this.funcoes = usuario.getFuncoes().stream()
                    .map(func -> new FuncaoDTO(func))
                    .collect(Collectors.toSet());
        }
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

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

    public Set<FuncaoDTO> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<FuncaoDTO> funcoes) {
        this.funcoes = funcoes;
    }
}
