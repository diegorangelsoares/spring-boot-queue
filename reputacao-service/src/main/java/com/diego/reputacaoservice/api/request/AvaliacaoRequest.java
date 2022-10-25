package com.diego.reputacaoservice.api.request;

import lombok.Data;

@Data
public class AvaliacaoRequest {

    private long idRestautante;

    private String descricao;

    private Double nota;


}
