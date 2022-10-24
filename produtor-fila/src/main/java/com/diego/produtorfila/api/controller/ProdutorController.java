package com.diego.produtorfila.api.controller;

import Contants.RabbitMQContantes;
import com.diego.produtorfila.service.RabbitMqService;
import dto.EstoqueDTO;
import dto.PrecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ProdutorController {

    @Autowired
    RabbitMqService rabbitMqService;

    @RequestMapping(value = "/estoque", method = RequestMethod.GET)
    public ResponseEntity<?> getEstoque (){

        return new ResponseEntity<>("Aloww", HttpStatus.OK);
    }

    @RequestMapping(value = "/estoque", method = RequestMethod.PUT)
    public ResponseEntity<?> alteraEstoque (@RequestBody EstoqueDTO estoqueDTO){
        log.info("Enviando mensagem estoque: "+estoqueDTO.toString());
        this.rabbitMqService.enviaMensagem(RabbitMQContantes.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity<>("Alteração de estoque enviada...", HttpStatus.OK);
    }

    @RequestMapping(value = "/preco", method = RequestMethod.PUT)
    public ResponseEntity<?> alteraPreco (@RequestBody PrecoDTO precoDTO){
        log.info("Enviando mensagem preco: "+precoDTO.toString());
        this.rabbitMqService.enviaMensagem(RabbitMQContantes.FILA_PRECO, precoDTO);
        return new ResponseEntity<>("Alteração de preço enviada...", HttpStatus.OK);
    }

}
