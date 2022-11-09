package com.diego.produtorfila.security;

import org.springframework.context.annotation.Bean;
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

    private final PasswordEncoder passwordEncoder;

    private static final String[] ENDPOINTS_LIBERADOS = {
//            "/tokens/**",
//            "/login/**",
            "/webjars/**",
            "/v1/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html"
//            ,
//            "/actuator/**",
//            "/**"
    };

    public JWTConfiguracao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
                .antMatchers(ENDPOINTS_LIBERADOS).permitAll()
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
