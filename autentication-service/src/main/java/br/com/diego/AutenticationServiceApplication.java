package br.com.diego;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication()
public class AutenticationServiceApplication {

	public static void main(String[] args) throws UnknownHostException {
		Logger logger = LoggerFactory.getLogger(AutenticationServiceApplication.class);
		SpringApplication app = new SpringApplication(AutenticationServiceApplication.class);
		Environment env = app.run(args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
						"Autentication Service está rodando! Acesse uma das URLs:\n\t" +
						"Para obter um token para consumir as APIs: " +
//						"Curl\thttp://localhost:{}//login\n\t" +
						"\n\n\tcurl --location --request POST 'localhost:{}/login' \\\n" +
								"\t--header 'Content-Type: application/json' \\\n" +
								"\t--data-raw '{\n" +
								"\t    \"login\":\"nomeUsuario\",\n" +
								"\t    \"password\": \"senhaUsuario\"\n" +
								"\t}'\n\n"+

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
