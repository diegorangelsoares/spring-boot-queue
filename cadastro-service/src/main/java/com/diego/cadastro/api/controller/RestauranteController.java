package com.diego.cadastro.api.controller;

import com.diego.cadastro.api.request.RestauranteResquest;
import com.diego.cadastro.model.Restaurante;
import com.diego.cadastro.services.RestauranteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/v1/restaurante")
@Slf4j
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> cadastra (@RequestBody RestauranteResquest restauranteResquest){
        Restaurante restaurante = new Restaurante();
        restaurante.setEndereco(restauranteResquest.getEndereco());
        restaurante.setNome(restauranteResquest.getNome());
        restaurante.setNotaMedia(restauranteResquest.getNotaMedia());
        return new ResponseEntity<>(restauranteService.salva(restaurante), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<?> altera (@RequestBody Restaurante restaurante){
        return new ResponseEntity<>(restauranteService.altera(restaurante), HttpStatus.OK);
    }

    @RequestMapping (value = "/{codigo}", method = RequestMethod.GET)
    public ResponseEntity<?> getById (@PathVariable long codigo){
        return new ResponseEntity<>(restauranteService.buscaRestaurante(codigo), HttpStatus.OK);
    }

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll (){
        List<Restaurante> restaurantes =restauranteService.buscaTodosRestaurantes();
        restauranteService.buscaMediaTodosRestaurantes(restaurantes);
        return new ResponseEntity<>(restauranteService.buscaTodosRestaurantes(), HttpStatus.OK);
    }

}
