package com.diego.consumidorfila.api.request;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ProdutoResquest {

    private String descricao;

    private int quantidade;

    private Double preco;


}
