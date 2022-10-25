package com.diego.reputacaoservice.services;

import com.diego.reputacaoservice.api.request.AvaliacaoRequest;
import com.diego.reputacaoservice.model.Avaliacao;

import java.util.List;


public interface AvaliacaoService {

    public Avaliacao salva (AvaliacaoRequest produto);

    public List<Avaliacao> listaPorRestaurante (long idRestaurante);


}
