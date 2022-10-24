package com.diego.consumidorfila.consumer;

import Contants.RabbitMQContantes;
import dto.EstoqueDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class EstoqueConsumer implements Serializable {

    @RabbitListener (queues = RabbitMQContantes.FILA_ESTOQUE)
    private void consumidor(EstoqueDTO estoqueDTO){
        log.info("Resgatando mensagem do RabbitMQ: "+estoqueDTO.toString());
    }

}
