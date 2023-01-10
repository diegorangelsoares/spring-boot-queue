package com.diego.cadastro.services;

import com.diego.cadastro.model.Cliente;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClienteService {

    @Cacheable(value = "clientes")
    public Cliente getClienteById(Long idCliente);

    @CacheEvict(value = "clientes", allEntries=true)
    public Cliente save (Cliente cliente);

    @Cacheable(value = "clientes")
    public List<Cliente> getAll ();

    @Cacheable(value = "clientes")
    public Page<Cliente> getAllPage(int page, int size);

}
