package br.com.nexo.dto;

import jakarta.validation.constraints.NotNull;
import br.com.nexo.model.EnumFuncao;
import br.com.nexo.model.Funcao;

public class FuncaoDTO {

    private Long idFuncao;

    @NotNull
    private EnumFuncao nmFuncao;

    public FuncaoDTO() {
    }

    public FuncaoDTO(Funcao funcao) {
        if (funcao != null) {
            this.idFuncao = funcao.getIdFuncao();
            this.nmFuncao = funcao.getNmFuncao();
        }
    }

    public Long getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(Long idFuncao) {
        this.idFuncao = idFuncao;
    }

    public EnumFuncao getNmFuncao() {
        return nmFuncao;
    }

    public void setNmFuncao(EnumFuncao nmFuncao) {
        this.nmFuncao = nmFuncao;
    }
}
