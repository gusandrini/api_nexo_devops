package br.com.nexo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "T_NX_PREDICAO")
public class Predicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "predicao_seq")
    @SequenceGenerator(name = "predicao_seq", sequenceName = "SEQ_T_NX_PREDICAO", allocationSize = 1)
    @Column(name = "id_predicao")
    private Long idPredicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_descricao")
    @NotNull
    private DescricaoCliente descricaoCliente;

    @NotNull
    @Min(0)
    @Max(999)
    @Column(name = "ds_resultado_predicao")
    private Integer dsResultadoPredicao;

    @Size(max = 40)
    @Column(name = "ds_recomendacao", length = 40)
    private String dsRecomendacao;

    public Predicao() {
    }

    public Long getIdPredicao() {
        return idPredicao;
    }

    public void setIdPredicao(Long idPredicao) {
        this.idPredicao = idPredicao;
    }

    public DescricaoCliente getDescricaoCliente() {
        return descricaoCliente;
    }

    public void setDescricaoCliente(DescricaoCliente descricaoCliente) {
        this.descricaoCliente = descricaoCliente;
    }

    public Integer getDsResultadoPredicao() {
        return dsResultadoPredicao;
    }

    public void setDsResultadoPredicao(Integer dsResultadoPredicao) {
        this.dsResultadoPredicao = dsResultadoPredicao;
    }

    public String getDsRecomendacao() {
        return dsRecomendacao;
    }

    public void setDsRecomendacao(String dsRecomendacao) {
        this.dsRecomendacao = dsRecomendacao;
    }
}
