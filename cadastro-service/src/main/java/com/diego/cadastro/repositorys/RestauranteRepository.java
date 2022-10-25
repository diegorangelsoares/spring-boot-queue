package com.diego.cadastro.repositorys;

import com.diego.cadastro.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

//    Optional<List<Restaurante>> findAll(Pageable pageable);
}
