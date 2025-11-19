package br.com.nexo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_nx_influencia_fam")
public class InfluenciaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_influencia")
    private Long idInfluenciaFamiliar;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_nivel_influencia", length = 50, nullable = false)
    @NotNull
    private EnumInfluenciaFamiliar nmInfluenciaFamiliar;

    public Long getIdInfluenciaFamiliar() {
        return idInfluenciaFamiliar;
    }

    public void setIdInfluenciaFamiliar(Long idInfluenciaFamiliar) {
        this.idInfluenciaFamiliar = idInfluenciaFamiliar;
    }

    public EnumInfluenciaFamiliar getNmInfluenciaFamiliar() {
        return nmInfluenciaFamiliar;
    }

    public void setNmInfluenciaFamiliar(EnumInfluenciaFamiliar nmInfluenciaFamiliar) {
        this.nmInfluenciaFamiliar = nmInfluenciaFamiliar;
    }
}
