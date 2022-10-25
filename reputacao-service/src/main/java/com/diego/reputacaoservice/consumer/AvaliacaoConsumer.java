package com.diego.reputacaoservice.consumer;

import Contants.RabbitMQContantes;
import dto.EstoqueDTO;
import dto.PrecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class AvaliacaoConsumer implements Serializable {

//    @Autowired
//    ProdutoServiceImp estoqueService;
//
//    @RabbitListener (queues = RabbitMQContantes.FILA_ESTOQUE)
//    private void consumidor(EstoqueDTO estoqueDTO){
//        log.info("Resgatando mensagem de estoque do RabbitMQ: "+estoqueDTO.toString());
//        estoqueService.atualizaEstoque(estoqueDTO);
//    }
//
//    @RabbitListener (queues = RabbitMQContantes.FILA_PRECO)
//    private void consumidor(PrecoDTO precoDTO){
//        log.info("Resgatando mensagem de preco do RabbitMQ: "+precoDTO.toString());
//        estoqueService.atualizaPreco(precoDTO)
//        ;
//    }

}
