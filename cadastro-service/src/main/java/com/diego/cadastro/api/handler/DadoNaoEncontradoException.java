package com.diego.cadastro.api.handler;

public class DadoNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 6707532483580150146L;

    
    public DadoNaoEncontradoException(String message) {
        super(message);
    }
    
}
