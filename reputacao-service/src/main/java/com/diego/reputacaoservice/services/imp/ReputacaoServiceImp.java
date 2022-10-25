package com.diego.reputacaoservice.services.imp;

import com.diego.reputacaoservice.api.request.AvaliacaoRequest;
import com.diego.reputacaoservice.model.Avaliacao;
import com.diego.reputacaoservice.model.Reputacao;
import com.diego.reputacaoservice.repositorys.AvaliacaoRepository;
import com.diego.reputacaoservice.repositorys.ReputacaoRepository;
import com.diego.reputacaoservice.services.AvaliacaoService;
import com.diego.reputacaoservice.services.ReputacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReputacaoServiceImp implements ReputacaoService {

    @Autowired
    ReputacaoRepository reputacaoRepository;

    @Override
    public Reputacao salva(Reputacao reputacao) {
        return reputacaoRepository.save(reputacao);
    }

    @Override
    public Reputacao listaPorRestaurante(long idRestaurante) {
        if (!reputacaoRepository.findByIdRestautante(idRestaurante).isPresent()){
            Reputacao reputacao = new Reputacao();
            reputacao.setIdRestautante(idRestaurante);
            reputacao.setMedia(0.0);
            reputacao.setQuantidadeAvaliacoes(0);
            reputacaoRepository.save(reputacao);
            return reputacao;
        }
        return reputacaoRepository.findByIdRestautante(idRestaurante).get();
    }
}
