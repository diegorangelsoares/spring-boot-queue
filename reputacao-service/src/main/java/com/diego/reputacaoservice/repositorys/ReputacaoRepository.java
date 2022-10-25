package com.diego.reputacaoservice.repositorys;

import com.diego.reputacaoservice.model.Reputacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReputacaoRepository extends JpaRepository<Reputacao, Long> {

    Optional<Reputacao> findByIdRestautante(long idRestaurante);
}
