package com.diego.cadastro.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "prato")
public class Prato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigo;

    private String descricao;

    private String ingredientes;

    private Double preco;

    @ManyToOne
    private Restaurante restaurante;

}
