package com.diego.cadastro.services;


import com.diego.cadastro.api.request.PratoResquest;
import com.diego.cadastro.model.Prato;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PratoService {

    public Prato salva (PratoResquest prato);

    public Prato salva (Prato prato);

    public Prato altera (Prato prato);

    public Optional<Prato> buscaPrato(long codigo, long idRestaurante);

    public Optional<List<Prato>> buscaTodosPratos(long idRestaurante);

}
