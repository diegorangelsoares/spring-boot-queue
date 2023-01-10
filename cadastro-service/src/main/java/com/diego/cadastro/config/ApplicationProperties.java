package com.diego.cadastro.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Set;

@Data
@ConfigurationProperties(prefix = "cadastroservice")
public class ApplicationProperties {
    
    @Value("${spring.application.name}")
    private String name;

    private ApiInfo apiInfo = new ApiInfo();
    private AwsInfo aws = new AwsInfo();
    private Queue queue = new Queue();

    @Data
    public static class ApiInfo {
        private String titulo;
        private String descricao;
        private String versao;
        private String pacote;
    }
    
    @Data
    public static class AwsInfo {
        private String uri;
        private String region;
        private String accessKey;
        private String secretKey;
    }
    
    @Data
    public static class Queue {
        private boolean create;
        private Set<String> active;
        private List<QueueData> data;
        

        @Data
        public static class QueueData {
            private String name;
            private boolean dlq;
            private boolean active;
        }
    }

}
