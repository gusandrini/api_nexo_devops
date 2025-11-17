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
@Table(name = "tb_nx_influencia_familiar")
public class InfluenciaFamiliar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_influencia_familiar")
    private Long idInfluenciaFamiliar;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_influencia_familiar", length = 50)
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
