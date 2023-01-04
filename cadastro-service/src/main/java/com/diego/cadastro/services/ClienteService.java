package com.diego.cadastro.services;

import com.diego.cadastro.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {

    public Cliente getClienteById(long idCliente);

}
