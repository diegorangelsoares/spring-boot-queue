package com.diego.reputacaoservice.services.imp;

import com.diego.reputacaoservice.api.request.AvaliacaoRequest;
import com.diego.reputacaoservice.model.Avaliacao;
import com.diego.reputacaoservice.model.Reputacao;
import com.diego.reputacaoservice.repositorys.AvaliacaoRepository;
import com.diego.reputacaoservice.repositorys.ReputacaoRepository;
import com.diego.reputacaoservice.services.AvaliacaoService;
import dto.AvaliacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoServiceImp implements AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    ReputacaoRepository reputacaoRepository;

    @Override
    public Avaliacao salva(AvaliacaoDTO avaliacao) {
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setDescricao(avaliacao.getDescricao());
        avaliacao1.setNota(avaliacao.getNota());
        avaliacao1.setIdRestautante(avaliacao.getIdRestautante());
        Reputacao reputacao = new Reputacao();
        if (!reputacaoRepository.findByIdRestautante(avaliacao.getIdRestautante()).isPresent()){
            reputacao.setIdRestautante(avaliacao.getIdRestautante());
            reputacao.setMedia(0.0);
            reputacao.setQuantidadeAvaliacoes(0);
            reputacaoRepository.save(reputacao);
        }else{
            reputacao = reputacaoRepository.findByIdRestautante(avaliacao.getIdRestautante()).get();
        }
        Double media = reputacao.getMedia() + avaliacao.getNota();
        long quantidadeAtualizada = reputacao.getQuantidadeAvaliacoes() + 1;
        media = media / (quantidadeAtualizada);
        reputacao.setMedia(media);
        reputacao.setQuantidadeAvaliacoes(quantidadeAtualizada);
        reputacaoRepository.save(reputacao);
        return avaliacaoRepository.save(avaliacao1);
    }

    @Override
    public List<Avaliacao> listaPorRestaurante(long idRestaurante) {
        Optional<List<Avaliacao>> avaliacoes = avaliacaoRepository.findAllByIdRestautante(idRestaurante);
        if (!avaliacoes.isPresent()){
            return new ArrayList<Avaliacao>();
        }
        return avaliacaoRepository.findAllByIdRestautante(idRestaurante).get();
    }
}
