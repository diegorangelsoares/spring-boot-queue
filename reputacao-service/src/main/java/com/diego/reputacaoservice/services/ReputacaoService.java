package com.diego.reputacaoservice.services;

import com.diego.reputacaoservice.model.Reputacao;

public interface ReputacaoService {

    public Reputacao salva (Reputacao reputacao);

    public Reputacao listaPorRestaurante (long idRestaurante);


}
