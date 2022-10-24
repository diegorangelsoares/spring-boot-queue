package com.diego.consumidorfila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ConsumidorFilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorFilaApplication.class, args);
		System.out.println("API UP\nURL: http://localhost:8082/swagger-ui/index.html");
	}

}
