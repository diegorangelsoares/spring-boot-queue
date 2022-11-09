package dto;

import java.io.Serializable;

public class AvaliacaoDTO implements Serializable {

    private long idRestautante;

    private String descricao;

    private Double nota;

    public long getIdRestautante() {
        return idRestautante;
    }

    public void setIdRestautante(long idRestautante) {
        this.idRestautante = idRestautante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
