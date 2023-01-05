package com.diego.cadastro.services;

import com.diego.cadastro.model.Cliente;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClienteService {

    @Cacheable(value = "clientes", key = "#idCliente")
    public Cliente getClienteById(Long idCliente);

    public Cliente save (Cliente cliente);

    public List<Cliente> getAll ();

    public Page<Cliente> getAllPage(int page, int size);

}
