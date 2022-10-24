package com.diego.consumidorfila.consumer;

import Contants.RabbitMQContantes;
import com.diego.consumidorfila.model.Estoque;
import com.diego.consumidorfila.services.EstoqueService;
import dto.EstoqueDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class EstoqueConsumer implements Serializable {

    @Autowired
    EstoqueService estoqueService;

    @RabbitListener (queues = RabbitMQContantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO estoqueDTO){
        log.info("Resgatando mensagem do RabbitMQ: "+estoqueDTO.toString());
        Estoque estoque = new Estoque();
        estoque.setCodigoProduto(estoqueDTO.codigoProduto);
        estoque.setQuantidade(estoqueDTO.quantidade);
        estoqueService.atualizaEstoque(estoque);
    }

}
