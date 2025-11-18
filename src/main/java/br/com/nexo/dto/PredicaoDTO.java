package br.com.nexo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PredicaoDTO {

    private Long idPredicao;

    @NotNull
    private Long idDescricao;

    @NotNull
    @Min(0)
    @Max(999)
    private Integer dsResultadoPredicao;

    @Size(max = 40)
    private String dsRecomendacao;

    public PredicaoDTO() {
    }

    public Long getIdPredicao() {
        return idPredicao;
    }

    public void setIdPredicao(Long idPredicao) {
        this.idPredicao = idPredicao;
    }

    public Long getIdDescricao() {
        return idDescricao;
    }

    public void setIdDescricao(Long idDescricao) {
        this.idDescricao = idDescricao;
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
