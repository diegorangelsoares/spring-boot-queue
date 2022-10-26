package com.diego.consumidorfila.api.controller;

import com.diego.consumidorfila.api.request.ProdutoResquest;
import com.diego.consumidorfila.model.Produto;
import com.diego.consumidorfila.services.imp.ProdutoServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ProdutoController {

    @Autowired
    ProdutoServiceImp estoqueService;

    @RequestMapping(value = "/produto/{idProduto}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduto (@PathVariable long idProduto){
        Optional<Produto> estoque = estoqueService.buscaProduto(idProduto);
        if (!estoque.isPresent()){
            return new ResponseEntity<>("Produto não encontrado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(estoque.get(), HttpStatus.OK);
    }

    @RequestMapping (value = "/produto", method = RequestMethod.POST)
    public ResponseEntity<?> saveProduto (@RequestBody ProdutoResquest produtoResquest){
        Produto produto = new Produto();
        produto.setDescricao(produtoResquest.getDescricao());
        produto.setPreco(produtoResquest.getPreco());
        produto.setQuantidade(produtoResquest.getQuantidade());
        produto.setDataAtualizacao(LocalDateTime.now());
        return new ResponseEntity<>(estoqueService.salva(produto), HttpStatus.OK);
    }

}
