
package br.com.nexo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Digits;

@Entity
@Table(name = "TB_NX_DESCRICAO_CLIENTE")
public class DescricaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descricao")
    private Long idDescricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @NotNull
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacao")
    @NotNull
    private Ocupacao ocupacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campo_estudo")
    @NotNull
    private CampoEstudo campoEstudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_educacao")
    @NotNull
    private NivelEducacional nivelEducacional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_influencia")
    @NotNull
    private InfluenciaFamiliar influenciaFamiliar;


    @Column(name = "qtde_anos_experiencia", nullable = false)
    @NotNull
    @Min(0)
    @Max(999)
    private Integer qtdaAnosExperiencia;


    @Column(name = "ds_satisfacao", nullable = false)
    @NotNull
    @Min(0)
    @Max(99)
    private Integer dsSatisfacao;


    @Column(name = "ds_tecnologia", nullable = false)
    @NotNull
    @Min(0)
    @Max(99)
    private Integer dsTecnologia;


    @Column(name = "ds_mudanca", nullable = false)
    @NotNull
    @Min(0)
    @Max(99)
    private Integer dsMudanca;

    @Column(name = "dt_input", nullable = false)
    @NotNull
    private LocalDateTime dtInput;


    @Column(name = "nr_idade", nullable = true)
    @Min(0)
    @Max(999)
    private Integer nrIdade;

    @Column(name = "nr_salario", nullable = true)
    @Min(0)
    private Float nrSalario;
    public Integer getNrIdade() {
        return nrIdade;
    }
    
    public DescricaoCliente() {
    }

    public Long getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(Long idDescricao) {
        this.idDescricao = idDescricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public CampoEstudo getCampoEstudo() {
        return campoEstudo;
    }

    public void setCampoEstudo(CampoEstudo campoEstudo) {
        this.campoEstudo = campoEstudo;
    }

    public NivelEducacional getNivelEducacional() {
        return nivelEducacional;
    }

    public void setNivelEducacional(NivelEducacional nivelEducacional) {
        this.nivelEducacional = nivelEducacional;
    }

    public InfluenciaFamiliar getInfluenciaFamiliar() {
        return influenciaFamiliar;
    }

    public void setInfluenciaFamiliar(InfluenciaFamiliar influenciaFamiliar) {
        this.influenciaFamiliar = influenciaFamiliar;
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
    
    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }

    public Float getNrSalario() {
        return nrSalario;
    }

    public void setNrSalario(Float nrSalario) {
        this.nrSalario = nrSalario;
    }
    
}
