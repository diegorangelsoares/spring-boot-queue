package com.diego.cadastro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity (name = "t_cliente")
public class Cliente {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String telefone;

    private String endereco;

}
