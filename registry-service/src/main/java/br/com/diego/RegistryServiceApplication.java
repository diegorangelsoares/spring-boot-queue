package br.com.diego;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaServer
public class RegistryServiceApplication {

	public static void main(String[] args) throws UnknownHostException {
		Logger logger = LoggerFactory.getLogger(RegistryServiceApplication.class);
		SpringApplication app = new SpringApplication(RegistryServiceApplication.class);
		Environment env = app.run(args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
						"Registry Service está rodando! \n\t" +
						"Local: \thttp://localhost:{}/\n\t" +

						env.getProperty("server.port"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

}
