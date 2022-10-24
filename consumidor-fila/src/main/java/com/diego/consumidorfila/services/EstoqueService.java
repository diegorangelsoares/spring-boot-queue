package com.diego.consumidorfila.services;

import com.diego.consumidorfila.model.Estoque;
import com.diego.consumidorfila.repositorys.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void atualizaEstoque (Estoque estoque){
        estoqueRepository.save(estoque);
    }

}
