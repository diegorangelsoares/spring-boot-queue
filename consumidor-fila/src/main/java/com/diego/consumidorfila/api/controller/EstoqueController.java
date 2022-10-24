package com.diego.consumidorfila.api.controller;

import com.diego.consumidorfila.model.Estoque;
import com.diego.consumidorfila.services.EstoqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @RequestMapping(value = "/estoque/{idProduto}", method = RequestMethod.GET)
    public ResponseEntity<?> getEstoque (@PathVariable long idProduto){
        Optional<Estoque> estoque = estoqueService.buscaEstoque(idProduto);
        if (!estoque.isPresent()){
            return new ResponseEntity<>("Produto não encontrado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(estoque.get(), HttpStatus.OK);
    }

}
