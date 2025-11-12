package br.com.nexo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "TB_NX_FUNCAO")
public class Funcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcao")
    private Long idFuncao;

    @Column(name = "nm_funcao", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumFuncao nmFuncao;

    @ManyToMany(mappedBy = "funcoes", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    public Funcao() {
    }

    public Funcao(Long idFuncao, EnumFuncao nmFuncao) {
        this.idFuncao = idFuncao;
        this.nmFuncao = nmFuncao;
    }

    public Long getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Long idFuncao) {
        this.idFuncao = idFuncao;
    }

    public EnumFuncao getNmFuncao() {
        return nmFuncao;
    }

    public void setNmFuncao(EnumFuncao nmFuncao) {
        this.nmFuncao = nmFuncao;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
