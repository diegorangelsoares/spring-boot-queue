package com.diego.cadastro.services.imp;

import com.diego.cadastro.config.exception.NaoEncontradoException;
import com.diego.cadastro.model.Cliente;
import com.diego.cadastro.repositorys.ClienteRepository;
import com.diego.cadastro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public Cliente getClienteById(long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).get();
        if (cliente == null){
            throw new NaoEncontradoException("Cliente não encontrado para o id: "+idCliente);
        }
        return clienteRepository.findById(idCliente).get();
    }
}
