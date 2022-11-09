package com.diego.reputacaoservice.services;

import com.diego.reputacaoservice.api.request.AvaliacaoRequest;
import com.diego.reputacaoservice.model.Avaliacao;
import dto.AvaliacaoDTO;

import java.util.List;


public interface AvaliacaoService {

    public Avaliacao salva (AvaliacaoDTO produto);

    public List<Avaliacao> listaPorRestaurante (long idRestaurante);


}
