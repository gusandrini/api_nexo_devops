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
@Table(name = "TB_NX_CAMPO_ESTUDO")
public class CampoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campo_estudo")
    private Long idCampoEstudo;

    @Column(name = "nm_campo_estudo", length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String nmCampoEstudo;

    public Long getIdCampoEstudo() {
        return idCampoEstudo;
    }

    public void setIdCampoEstudo(Long idCampoEstudo) {
        this.idCampoEstudo = idCampoEstudo;
    }

    public String getNmCampoEstudo() {
        return nmCampoEstudo;
    }

    public void setNmCampoEstudo(String nmCampoEstudo) {
        this.nmCampoEstudo = nmCampoEstudo;
    }
}
