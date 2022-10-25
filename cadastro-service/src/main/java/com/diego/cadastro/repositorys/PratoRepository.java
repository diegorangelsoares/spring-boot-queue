package com.diego.cadastro.repositorys;

import com.diego.cadastro.model.Prato;
import com.diego.cadastro.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

    Optional<Prato> findByCodigoAndRestaurante(long codigo, Restaurante restaurante);

    Optional<List<Prato>> findByRestaurante(Restaurante restaurante);

}
