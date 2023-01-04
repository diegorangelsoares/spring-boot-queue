package com.diego.cadastro.repositorys;

import com.diego.cadastro.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    public Optional<Cliente> findById(long idCliente);

}
