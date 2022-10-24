package com.diego.produtorfila.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrecoDTO implements Serializable {

    public String codigoProduto;
    public Double preco;

}
