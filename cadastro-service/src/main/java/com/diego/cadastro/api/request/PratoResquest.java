package com.diego.cadastro.api.request;

import lombok.Data;

@Data
public class PratoResquest {

    private String descricao;

    private String ingredientes;

    private Double preco;

    private long idrestaurante;


}
