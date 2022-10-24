package com.diego.consumidorfila.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class EstoqueController {

    @RequestMapping(value = "/estoque", method = RequestMethod.GET)
    public ResponseEntity<?> getEstoque (){

        return new ResponseEntity<>("Consumindo Estoque atual...", HttpStatus.OK);
    }

}
