package com.diego.cadastro.api.controller;

import com.diego.cadastro.model.Cliente;
import com.diego.cadastro.services.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.awt.print.Pageable;

@Slf4j
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

//    @Cacheable(cacheNames = "clientes", key = "#idCliente")
    @RequestMapping (value = "/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientes(@PathVariable long idCliente){
        log.info("Buscando cliente de id: "+idCliente);
        return new ResponseEntity<>(clienteService.getClienteById(idCliente), HttpStatus.OK);
    }

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getClientesAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        log.info("Buscando clientes...");
        return new ResponseEntity<>(clienteService.getAllPage(page, size), HttpStatus.OK);
    }

//    @CacheEvict(cacheNames = "clientes", key="#idCliente")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save (@RequestBody Cliente cliente){
        log.info("Salvando cliente: \n" + cliente.toString());
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.ACCEPTED);
    }
}
