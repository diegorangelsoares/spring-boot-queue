package br.com.diego.security;

import br.com.diego.service.UsuarioDetalheServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final UsuarioDetalheServiceImp usuarioService;
    private final PasswordEncoder passwordEncoder;

    private static final String[] ENDPOINTS_LIBERADOS = {
            "/tokens/**",
            "/login/**",
            "/webjars/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/usuarios/senha",
            "/usuarios/recuperacao-senha/email",
            "/usuarios/recuperacao-senha/login",
            "/usuarios/recuperacao-login/email",
            "/usuarios/validacao/token",
            "/usuarios/credenciais",
            "/actuator/**"
    };


    public JWTConfiguracao(UsuarioDetalheServiceImp usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                //.antMatchers(ENDPOINTS_LIBERADOS).permitAll()
//                .antMatchers("/*").permitAll()
//                .and().authorizeRequests().antMatchers(ENDPOINTS_LIBERADOS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    //Liberar requisicao externa
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
