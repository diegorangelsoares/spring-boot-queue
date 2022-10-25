package com.diego.cadastro.services.imp;

import com.diego.cadastro.model.Restaurante;
import com.diego.cadastro.repositorys.RestauranteRepository;
import com.diego.cadastro.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServiceImp implements RestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Override
    public Restaurante salva(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante altera(Restaurante restaurante) {
        Optional<Restaurante> restaurante1 = restauranteRepository.findById(restaurante.getCodigo());
        if (restaurante1.isPresent()){
            return restauranteRepository.save(restaurante1.get());
        }else{
            return null;
        }
    }

    @Override
    public Optional<Restaurante> buscaRestaurante(long codigo) {
        return restauranteRepository.findById(codigo);
    }

    @Override
    public List<Restaurante> buscaTodosRestaurantes() {
        return restauranteRepository.findAll();
    }

    @Autowired
    public List<Restaurante> buscaMediaTodosRestaurantes(List<Restaurante> restaurantes){
        if (restaurantes != null && !restaurantes.isEmpty()){
            //buscar no reputacao-service
            restaurantes.forEach(
                    restaurante -> {
                        //feign para o servico para alterar a media exibida
                        restaurante.setNotaMedia(0.5);
                    }
            );
        }
        return restaurantes;
    };
}
