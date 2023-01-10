package com.diego.cadastro.api.handler;

public class DadoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 6707532483580150146L;


    public DadoInvalidoException(String message) {
        super(message);
    }
    
}
