package com.diego.cadastro.api.handler;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.apache.commons.lang3.StringUtils.defaultString;

@ControllerAdvice
@RestControllerAdvice
public class ApplicationExceptionHandler  extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ DadoNaoEncontradoException.class })
    public ResponseEntity<Object> handlerDadoNaoEncontradoException(DadoNaoEncontradoException ex, WebRequest request) throws Exception {
        return this.handleException(ex, request, HttpStatus.NOT_FOUND, getMessageSource(defaultString(ex.getMessage(), "NOT_FOUND")));
    }

    @ExceptionHandler({ DadoJaExisteException.class })
    public ResponseEntity<Object> handlerDadoJaExisteException(DadoJaExisteException ex, WebRequest request) throws Exception {
        return this.handleException(ex, request, HttpStatus.BAD_REQUEST, getMessageSource(defaultString(ex.getMessage(), "BAD_REQUEST")));
    }

    @ExceptionHandler({ DadoInvalidoException.class })
    public ResponseEntity<Object> handlerDadoInvalidoException(DadoInvalidoException ex, WebRequest request) throws Exception {
        return this.handleException(ex, request, HttpStatus.BAD_REQUEST, getMessageSource(defaultString(ex.getMessage(), "BAD_REQUEST")));
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handlerRuntimeException(RuntimeException ex, WebRequest request) throws Exception {
        return this.handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, getMessageSource(defaultString(ex.getMessage(), "GENERIC_ERROR")));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private String getMessageSource(String defaultMessage) {
        return messageSource.getMessage(defaultMessage, null, defaultMessage, null);
    }

    protected ResponseEntity<Object> handleException(Exception ex, WebRequest request, HttpStatus status, String messageCode) {
        ErrorResponse errorResponse = new ErrorResponse(status, messageCode, definirPath(request));

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    private String definirPath(WebRequest request) {
        return request instanceof ServletWebRequest ? ((ServletWebRequest) request).getRequest().getRequestURI() : request.getContextPath();
    }

}
