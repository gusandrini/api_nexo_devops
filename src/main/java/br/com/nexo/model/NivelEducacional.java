package br.com.nexo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_nx_nivel_educacional")
public class NivelEducacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_educacional")
    private Long idNivelEducacional;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_nivel_educacional", length = 50)
    private EnumNivelEducacional nmNivelEducacional;

    public Long getIdNivelEducacional() {
        return idNivelEducacional;
    }

    public void setIdNivelEducacional(Long idNivelEducacional) {
        this.idNivelEducacional = idNivelEducacional;
    }

    public EnumNivelEducacional getNmNivelEducacional() {
        return nmNivelEducacional;
    }

    public void setNmNivelEducacional(EnumNivelEducacional nmNivelEducacional) {
        this.nmNivelEducacional = nmNivelEducacional;
    }
}
