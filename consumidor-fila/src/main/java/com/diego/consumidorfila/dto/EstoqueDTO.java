package com.diego.consumidorfila.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize
public class EstoqueDTO implements Serializable {

    public String codigoProduto;
    public String quantidade;

}
