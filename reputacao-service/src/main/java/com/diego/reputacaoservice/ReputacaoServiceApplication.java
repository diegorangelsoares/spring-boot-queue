package com.diego.reputacaoservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableWebMvc
public class ReputacaoServiceApplication {

	public static void main(String[] args) throws UnknownHostException {
		Logger logger = LoggerFactory.getLogger(ReputacaoServiceApplication.class);
		SpringApplication app = new SpringApplication(ReputacaoServiceApplication.class);
		Environment env = app.run(args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
						"Reputacao Service está rodando! Acesse uma das URLs:\n\t" +
						"Local Swagger: \thttp://localhost:{}/swagger-ui/index.html\n\t" +

						env.getProperty("server.port"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
