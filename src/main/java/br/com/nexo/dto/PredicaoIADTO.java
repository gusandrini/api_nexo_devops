package br.com.nexo.dto;

public class PredicaoIADTO {
    private Integer classePrevista;
    private Double probabilidadeMudar;

    public PredicaoIADTO() {}
    public PredicaoIADTO(Integer classePrevista, Double probabilidadeMudar) {
        this.classePrevista = classePrevista;
        this.probabilidadeMudar = probabilidadeMudar;
    }
    public Integer getClassePrevista() { return classePrevista; }
    public void setClassePrevista(Integer classePrevista) { this.classePrevista = classePrevista; }
    public Double getProbabilidadeMudar() { return probabilidadeMudar; }
    public void setProbabilidadeMudar(Double probabilidadeMudar) { this.probabilidadeMudar = probabilidadeMudar; }
}
