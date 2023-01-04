package com.diego.cadastro.api.controller;

import com.diego.cadastro.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @RequestMapping (value = "/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientes(@PathVariable long idCliente){

        return new ResponseEntity<>(clienteService.getClienteById(idCliente), HttpStatus.OK);
    }

}
