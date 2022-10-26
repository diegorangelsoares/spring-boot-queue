package com.diego.consumidorfila.services.imp;

import com.diego.consumidorfila.model.Produto;
import com.diego.consumidorfila.repositorys.ProdutoRepository;
import com.diego.consumidorfila.services.ProdutoService;
import dto.EstoqueDTO;
import dto.PrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProdutoServiceImp implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //@CacheEvict("estoque")
    public void atualizaEstoque (Produto produto){
        produtoRepository.save(produto);
    }

    public Produto salva (Produto produto){
        return produtoRepository.save(produto);
    }

//    @CacheEvict("estoque")
    public void atualizaEstoque (EstoqueDTO estoqueDTO){
        Optional<Produto> produto = buscaProduto(estoqueDTO.codigoProduto);
        if (produto.isPresent()){
            produto.get().setQuantidade(estoqueDTO.quantidade);
            produto.get().setDataAtualizacao(LocalDateTime.now());
            produtoRepository.save(produto.get());
        }
    }

//    @CacheEvict("estoque")
    public void atualizaPreco (PrecoDTO precoDTO){
        Optional<Produto> produto = buscaProduto(precoDTO.codigoProduto);
        if (produto.isPresent()){
            produto.get().setPreco(precoDTO.preco);
            produto.get().setDataAtualizacao(LocalDateTime.now());
            produtoRepository.save(produto.get());
        }
    }

//    @Cacheable("estoque")
    public Optional<Produto> buscaProduto(long codigo){
        return produtoRepository.findById(codigo);
    }


}
