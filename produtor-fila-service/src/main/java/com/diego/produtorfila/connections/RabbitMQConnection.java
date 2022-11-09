package com.diego.produtorfila.connections;

import Contants.RabbitMQContantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta (){
        return new DirectExchange(RabbitMQContantes.NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding (fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaEstoque = this.fila(RabbitMQContantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQContantes.FILA_PRECO);
        Queue filaAvaliacao = this.fila(RabbitMQContantes.FILA_AVALIACAO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);
        Binding ligacaoAvaliacao = this.relacionamento(filaAvaliacao, troca);

        //Criando as filas no RabitMQ
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);
        this.amqpAdmin.declareQueue(filaAvaliacao);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
        this.amqpAdmin.declareBinding(ligacaoAvaliacao);

    }

}
