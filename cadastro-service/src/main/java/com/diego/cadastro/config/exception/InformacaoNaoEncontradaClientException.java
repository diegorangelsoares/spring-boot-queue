package com.diego.cadastro.config.exception;

import com.diego.cadastro.config.annotation.BusinessException;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@BusinessException(key = "recurso.informacao-nao-encontrada-cliente", status = NOT_FOUND, returnMessageException = true)
public class InformacaoNaoEncontradaClientException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InformacaoNaoEncontradaClientException(String message) {
        super(message);
    }
}
