package br.com.nexo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_NX_OCUPACAO")
public class Ocupacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ocupacao")
    private Long idOcupacao;

    @Column(name = "nm_ocupacao", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nmOcupacao;

    public Long getIdOcupacao() {
        return idOcupacao;
    }

    public void setIdOcupacao(Long idOcupacao) {
        this.idOcupacao = idOcupacao;
    }

    public String getNmOcupacao() {
        return nmOcupacao;
    }

    public void setNmOcupacao(String nmOcupacao) {
        this.nmOcupacao = nmOcupacao;
    }
}
