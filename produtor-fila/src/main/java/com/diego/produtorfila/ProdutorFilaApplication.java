package com.diego.produtorfila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ProdutorFilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutorFilaApplication.class, args);
		System.out.println("API UP\nURL: http://localhost:8081/swagger-ui/index.html");
	}

}
