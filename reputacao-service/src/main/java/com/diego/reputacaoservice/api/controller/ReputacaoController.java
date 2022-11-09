package com.diego.reputacaoservice.api.controller;

import com.diego.reputacaoservice.api.request.AvaliacaoRequest;
import com.diego.reputacaoservice.services.AvaliacaoService;
import com.diego.reputacaoservice.services.ReputacaoService;
import dto.AvaliacaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/reputacao")
@Slf4j
public class ReputacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    ReputacaoService reputacaoService;

    @RequestMapping(value = "/restaurante/{idRestaurante}/avaliacoes", method = RequestMethod.GET)
    public ResponseEntity<?> getAvaliacoes (@PathVariable long idRestaurante){

        return new ResponseEntity<>(avaliacaoService.listaPorRestaurante(idRestaurante), HttpStatus.OK);
    }

    @RequestMapping(value = "/restaurante/{idRestaurante}/reputacao", method = RequestMethod.GET)
    public ResponseEntity<?> getReputacao (@PathVariable long idRestaurante){

        return new ResponseEntity<>(reputacaoService.listaPorRestaurante(idRestaurante), HttpStatus.OK);
    }

    @RequestMapping (value = "/avaliacaoSync", method = RequestMethod.POST)
    public ResponseEntity<?> saveAvaliacaoSync (@RequestBody AvaliacaoDTO avaliacaoRequest){
        return new ResponseEntity<>(avaliacaoService.salva(avaliacaoRequest), HttpStatus.OK);
    }

}
