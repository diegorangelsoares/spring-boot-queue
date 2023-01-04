package com.diego.cadastro.api.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus httpStatus, String message, String path) {
        this.timestamp= LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(HttpStatus httpStatus, List<String> messages, String path) {
        this.timestamp= LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = messages != null ? messages.stream().collect( Collectors.joining( ", " ) ) : null;
        this.path = path;
    }
}
