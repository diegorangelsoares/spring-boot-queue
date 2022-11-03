package com.diego.cadastro.api.controller;

import com.diego.cadastro.api.request.PratoResquest;
import com.diego.cadastro.api.request.RestauranteResquest;
import com.diego.cadastro.model.Prato;
import com.diego.cadastro.model.Restaurante;
import com.diego.cadastro.services.PratoService;
import com.diego.cadastro.services.RestauranteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/prato")
@Slf4j
public class PratoController {

    @Autowired
    PratoService pratoService;

    @Autowired
    RestauranteService restauranteService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastra (@RequestBody PratoResquest pratoResquest){
        Optional<Restaurante> restaurante = restauranteService.buscaRestaurante(pratoResquest.getIdrestaurante());
        if (!restaurante.isPresent()){
            return new ResponseEntity<>("Restaurante "+pratoResquest.getIdrestaurante()+" não localizado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pratoService.salva(pratoResquest), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> altera (@RequestBody Prato prato
                                    ,@RequestParam long idRestaurante){
        Optional<Prato> prato1 = pratoService.buscaPrato(prato.getCodigo(), idRestaurante);
        if (!prato1.isPresent()){
            return new ResponseEntity<>("Prato "+prato.getCodigo()+" não localizado!", HttpStatus.NOT_FOUND);
        }
        Optional<Restaurante> restaurante = restauranteService.buscaRestaurante(prato.getRestaurante().getCodigo());
        if (!restaurante.isPresent()){
            return new ResponseEntity<>("Restaurante "+prato.getRestaurante().getCodigo()+" não localizado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pratoService.altera(prato), HttpStatus.OK);
    }

    @RequestMapping (value = "/{codigo}", method = RequestMethod.GET)
    public ResponseEntity<?> getById (@PathVariable long codigo, @RequestParam long idRestaurante){
        Optional<Prato> prato = pratoService.buscaPrato(codigo, idRestaurante);
        if (!prato.isPresent()){
            return new ResponseEntity<>("Prato "+codigo+" não localizado para o restaurante "+idRestaurante+"!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pratoService.buscaPrato(codigo, idRestaurante), HttpStatus.OK);
    }

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll (@RequestParam long idRestaurante){
        Optional<Restaurante> restaurante = restauranteService.buscaRestaurante(idRestaurante);
        if (!restaurante.isPresent()){
            return new ResponseEntity<>("Restaurante "+idRestaurante+" não localizado!", HttpStatus.NOT_FOUND);
        }
        Optional<List<Prato>> pratos = pratoService.buscaTodosPratos(idRestaurante);
        if (!pratos.isPresent()){
            return new ResponseEntity<>(new ArrayList<Prato>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(pratoService.buscaTodosPratos(idRestaurante), HttpStatus.OK);
    }

}
