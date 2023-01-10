package com.diego.cadastro.services.imp;

import com.diego.cadastro.api.handler.DadoNaoEncontradoException;
import com.diego.cadastro.model.Cliente;
import com.diego.cadastro.repositorys.ClienteRepository;
import com.diego.cadastro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    public Cliente getClienteById(Long idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente == null || !cliente.isPresent()){
            throw new DadoNaoEncontradoException("Cliente não encontrado para o id: "+idCliente);
        }
        return clienteRepository.findById(idCliente).get();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Page<Cliente> getAllPage(int page, int size) {
            PageRequest pageRequest = PageRequest.of(
                    page,
                    size,
                    Sort.Direction.ASC,
                    "id");

            return clienteRepository.findAll(pageRequest);
    }


}
