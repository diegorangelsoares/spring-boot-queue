package com.diego.consumidorfila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableCaching
public class ConsumidorFilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorFilaApplication.class, args);
		System.out.println("API UP\nURL: http://localhost:8082/swagger-ui/index.html");
	}

}
