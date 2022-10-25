package com.diego.reputacaoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
//@EnableCaching
public class ReputacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReputacaoServiceApplication.class, args);
		System.out.println("API UP\nURL: http://localhost:8086/swagger-ui/index.html");
	}

}
