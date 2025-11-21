package br.com.nexo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class DescricaoClienteDTO {

    private Long idDescricao;

    private Long idUsuario;

    private Long idOcupacao;

    private Long idCampoEstudo;

    private Long idNivelEducacional;

    private Long idInfluenciaFamiliar;

    @Size(max = 30)
    private String dsOcupacao;

    @Min(0)
    @Max(999)
    private Integer qtdaAnosExperiencia;

    @Min(0)
    @Max(99)
    private Integer dsSatisfacao;

    @Min(0)
    @Max(99)
    private Integer dsTecnologia;

    @Min(0)
    @Max(99)
    private Integer dsMudanca;

    @Digits(integer = 15, fraction = 2)
    private Float nrSalario;
    
    @Min(0)
    @Max(999)
    private Integer nrIdade;
    
    private LocalDateTime dtInput;
    
    public Float getNrSalario() {
        return nrSalario;
    }

    public void setNrSalario(Float nrSalario) {
        this.nrSalario = nrSalario;
    }

    public Integer getNrIdade() {
        return nrIdade;
    }

    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }

    public DescricaoClienteDTO() {
    }

    public Long getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(Long idDescricao) {
        this.idDescricao = idDescricao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdOcupacao() {
        return idOcupacao;
    }

    public void setIdOcupacao(Long idOcupacao) {
        this.idOcupacao = idOcupacao;
    }

    public Long getIdCampoEstudo() {
        return idCampoEstudo;
    }

    public void setIdCampoEstudo(Long idCampoEstudo) {
        this.idCampoEstudo = idCampoEstudo;
    }

    public Long getIdNivelEducacional() {
        return idNivelEducacional;
    }

    public void setIdNivelEducacional(Long idNivelEducacional) {
        this.idNivelEducacional = idNivelEducacional;
    }

    public Long getIdInfluenciaFamiliar() {
        return idInfluenciaFamiliar;
    }

    public void setIdInfluenciaFamiliar(Long idInfluenciaFamiliar) {
        this.idInfluenciaFamiliar = idInfluenciaFamiliar;
    }

    
    public String getDsOcupacao() {
        return dsOcupacao;
    }

    public void setDsOcupacao(String dsOcupacao) {
        this.dsOcupacao = dsOcupacao;
    }

    public Integer getQtdaAnosExperiencia() {
        return qtdaAnosExperiencia;
    }

    public void setQtdaAnosExperiencia(Integer qtdaAnosExperiencia) {
        this.qtdaAnosExperiencia = qtdaAnosExperiencia;
    }

    public Integer getDsSatisfacao() {
        return dsSatisfacao;
    }

    public void setDsSatisfacao(Integer dsSatisfacao) {
        this.dsSatisfacao = dsSatisfacao;
    }

    public Integer getDsTecnologia() {
        return dsTecnologia;
    }

    public void setDsTecnologia(Integer dsTecnologia) {
        this.dsTecnologia = dsTecnologia;
    }

    public Integer getDsMudanca() {
        return dsMudanca;
    }

    public void setDsMudanca(Integer dsMudanca) {
        this.dsMudanca = dsMudanca;
    }

    public LocalDateTime getDtInput() {
        return dtInput;
    }

    public void setDtInput(LocalDateTime dtInput) {
        this.dtInput = dtInput;
    }
}
