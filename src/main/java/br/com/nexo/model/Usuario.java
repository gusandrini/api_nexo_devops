package br.com.nexo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.com.nexo.validation.ValidationGroups;

@Entity
@Table(name = "TB_NX_USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_NX_USUARIO_GEN")
    @SequenceGenerator(name = "SEQ_TB_NX_USUARIO_GEN", sequenceName = "SEQ_TB_NX_USUARIO", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nm_cliente", length = 80, nullable = false)
    @NotBlank
    @Size(max = 80)
    private String nmCliente;

    @Column(name = "nm_email", length = 80, nullable = false)
    @NotBlank
    @Email
    @Size(max = 80)
    private String nmEmail;

    @Column(name = "nm_senha", length = 100, nullable = false)
    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(max = 100)
    private String nmSenha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_FUNCAO_USUARIO", 
        joinColumns = @JoinColumn(name = "id_usuario"), 
        inverseJoinColumns = @JoinColumn(name = "id_funcao"))
    private Set<Funcao> funcoes = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nmCliente, String nmEmail, String nmSenha, Set<Funcao> funcoes) {
        this.idUsuario = idUsuario;
        this.nmCliente = nmCliente;
        this.nmEmail = nmEmail;
        this.nmSenha = nmSenha;
        this.funcoes = funcoes;
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

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
}
