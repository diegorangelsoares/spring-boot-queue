package com.diego.reputacaoservice.repositorys;

import com.diego.reputacaoservice.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Optional<List<Avaliacao>> findAllByIdRestautante(long idRestaurante);
}
