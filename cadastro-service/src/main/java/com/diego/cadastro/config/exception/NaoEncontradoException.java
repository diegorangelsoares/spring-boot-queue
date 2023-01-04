package com.diego.cadastro.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "cliente.nao-encontrado")
public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String message) {
        super(message);
    }
}

