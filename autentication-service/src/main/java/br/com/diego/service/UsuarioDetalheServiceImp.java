package br.com.diego.service;

import br.com.diego.data.DetalheUsuarioData;
import br.com.diego.model.Usuario;
import br.com.diego.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioDetalheServiceImp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetalheServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if (usuario.isEmpty()) throw new UsernameNotFoundException("Usuário ["+username+"] não encontrato!");
        return new DetalheUsuarioData(usuario);
    }
}
