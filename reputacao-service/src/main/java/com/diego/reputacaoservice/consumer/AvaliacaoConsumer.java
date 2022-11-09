package com.diego.reputacaoservice.consumer;

import Contants.RabbitMQContantes;
import com.diego.reputacaoservice.services.AvaliacaoService;
import dto.AvaliacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class AvaliacaoConsumer implements Serializable {

    @Autowired
    AvaliacaoService avaliacaoService;

    @RabbitListener (queues = RabbitMQContantes.FILA_AVALIACAO)
    private void consumidor(AvaliacaoDTO avaliacaoDTO){
        log.info("Resgatando mensagem de avaliacao do RabbitMQ: "+avaliacaoDTO.toString());
        avaliacaoService.salva(avaliacaoDTO)
        ;
    }

}
