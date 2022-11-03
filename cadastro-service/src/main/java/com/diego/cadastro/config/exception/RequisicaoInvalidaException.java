package com.diego.cadastro.config.exception;


import com.diego.cadastro.config.annotation.BusinessException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@BusinessException(key = "recurso.requisicao-invalida", status = BAD_REQUEST, returnMessageException = true)
public class RequisicaoInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RequisicaoInvalidaException(String message) {
        super(message);
    }

}

