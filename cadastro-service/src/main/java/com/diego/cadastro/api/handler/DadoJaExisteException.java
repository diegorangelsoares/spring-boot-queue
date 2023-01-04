package com.diego.cadastro.api.handler;

public class DadoJaExisteException extends RuntimeException {

    private static final long serialVersionUID = 6707532483580150146L;


    public DadoJaExisteException(String message) {
        super(message);
    }
    
}
