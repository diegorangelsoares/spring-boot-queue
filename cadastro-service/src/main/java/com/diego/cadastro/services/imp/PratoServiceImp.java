package com.diego.cadastro.services.imp;

import com.diego.cadastro.api.request.PratoResquest;
import com.diego.cadastro.model.Prato;
import com.diego.cadastro.model.Restaurante;
import com.diego.cadastro.repositorys.PratoRepository;
import com.diego.cadastro.repositorys.RestauranteRepository;
import com.diego.cadastro.services.PratoService;
import com.diego.cadastro.services.RestauranteService;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PratoServiceImp implements PratoService {

    @Autowired
    PratoRepository pratoRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    @Override
    public Prato salva(PratoResquest pratoResquest) {
        Prato prato = new Prato();
        prato.setDescricao(pratoResquest.getDescricao());
        prato.setPreco(pratoResquest.getPreco());
        prato.setIngredientes(pratoResquest.getIngredientes());
        Optional<Restaurante> restaurante = restauranteRepository.findById(pratoResquest.getIdrestaurante());
        prato.setRestaurante(restaurante.get());
        return this.salva(prato);
    }

    @Override
    public Prato salva(Prato prato) {
        return pratoRepository.save(prato);
    }

    @Override
    public Prato altera(Prato prato) {
        return this.salva(prato);
    }

    @Override
    public Optional<Prato> buscaPrato(long codigo, long idRestaurante) {
        Restaurante restaurante = restauranteRepository.findById(idRestaurante).get();
        return pratoRepository.findByCodigoAndRestaurante(codigo, restaurante);
    }

    @Override
    public Optional<List<Prato>> buscaTodosPratos(long idRestaurante) {
        Restaurante restaurante = restauranteRepository.findById(idRestaurante).get();
        Optional<List<Prato>> pratos = pratoRepository.findByRestaurante(restaurante);
        if (pratos.isPresent()){
            return pratos;
        }else{
            return null;
        }
    }
}
