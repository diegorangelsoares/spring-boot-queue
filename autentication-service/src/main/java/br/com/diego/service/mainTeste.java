package br.com.diego.service;

import br.com.diego.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class mainTeste {

//    @Autowired
    public static PasswordEncoder encoder;

    public static void main(String[] args) {



        Usuario usuario = new Usuario();
        usuario.setPassword("admin");
        System.out.println("Senha antes: "+usuario.getPassword());
        //usuario.setPassword(encoder.encode(usuario.getPassword()));
        System.out.println("Senha depois: "+encoder.encode(usuario.getPassword()));
    }


}
