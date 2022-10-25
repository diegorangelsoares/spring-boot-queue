package com.diego.cadastro.services;


import com.diego.cadastro.model.Restaurante;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface RestauranteService {

    public Restaurante salva (Restaurante restaurante);

    public Restaurante altera (Restaurante restaurante);

    public Optional<Restaurante> buscaRestaurante(long codigo);

    public List<Restaurante> buscaTodosRestaurantes();

}
