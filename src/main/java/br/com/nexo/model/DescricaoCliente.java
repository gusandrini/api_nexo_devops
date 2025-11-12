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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_NX_DESCRICAO_CLIENTE")
public class DescricaoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descricao")
    private Long idDescricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nm_area", length = 30)
    @Size(max = 30)
    private String nmArea;

    @Column(name = "ds_ocupacao", length = 30)
    @Size(max = 30)
    private String dsOcupacao;

    @Column(name = "qtda_anos_experiencia")
    @Min(0)
    private Integer qtdaAnosExperiencia;

    @Column(name = "ds_satisfacao")
    private Integer dsSatisfacao;

    @Column(name = "ds_tecnologia")
    private Integer dsTecnologia;

    @Column(name = "ds_mudanca")
    private Integer dsMudanca;

    @Column(name = "dt_input")
    private LocalDateTime dtInput;

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

    public String getNmArea() {
        return nmArea;
    }

    public void setNmArea(String nmArea) {
        this.nmArea = nmArea;
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
