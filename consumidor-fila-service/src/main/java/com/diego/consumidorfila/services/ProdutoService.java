package com.diego.consumidorfila.services;

import com.diego.consumidorfila.model.Produto;
import dto.EstoqueDTO;

import java.util.Optional;

public interface ProdutoService {

    public void atualizaEstoque (Produto produto);

    public void atualizaEstoque (EstoqueDTO estoqueDTO);

    public Optional<Produto> buscaProduto(long codigo);

    public Produto salva (Produto produto);


}
