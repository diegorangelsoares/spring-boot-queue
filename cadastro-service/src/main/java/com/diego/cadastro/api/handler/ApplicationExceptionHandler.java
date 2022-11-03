package com.diego.cadastro.api.handler;

import com.diego.cadastro.config.annotation.BusinessException;
import com.diego.cadastro.config.exception.UsuarioNaoLogadoException;
import com.diego.cadastro.dto.ErrorResponse;
import com.diego.cadastro.dto.FieldError;
import com.diego.cadastro.services.MessageService;
import org.slf4j.event.Level;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    protected final MessageService messageService;


    public ApplicationExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }
//
//    @ExceptionHandler({IntegrationException.class})
//    public ResponseEntity<Object> handleIntegrationException(IntegrationException integrationException, WebRequest request){
//        return handleExceptionIntegration(integrationException, integrationException.getHttpStatus(), request, null);
//    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        BusinessException businessExAnnotation = AnnotationUtils.findAnnotation(ex.getClass(), BusinessException.class);
        if (Objects.nonNull(businessExAnnotation))
            return handleException(ex, request, businessExAnnotation);

//        MessageTrace messageTrace = MessageTraceFactory.buildMessageTrace(ex.getMessage(), Level.ERROR.toString());
//        loggerInvoker.log(messageTrace, Level.ERROR);
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.operacao-invalida");
    }

    @ExceptionHandler({ UsuarioNaoLogadoException.class })
    public ResponseEntity<Object> handleUserNotLoggedException(UsuarioNaoLogadoException ex, WebRequest request) {
        return handleException(null, HttpStatus.UNAUTHORIZED, request, "seguranca.permisao-negada");
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return handleException(ex, HttpStatus.UNAUTHORIZED, request, "seguranca.permisao-negada");
    }

    protected ResponseEntity<Object> handleException(Exception ex, HttpStatus status, WebRequest request, String messageCode) {
        ErrorResponse errorResponse = new ErrorResponse(status, messageService.getMessage(messageCode), obterPath(request));
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
    		HttpStatus status, WebRequest request) {
    	ErrorResponse errorResponse = new ErrorResponse(status, "ARGUMENTO_INVALIDO", obterPath(request));
        List<FieldError> fieldErrors = obterListaErros(ex.getBindingResult());
        errorResponse.setFieldErrors(fieldErrors);
        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status, messageService.getMessage("recurso.requisicao-campo-obrigatorio"), obterPath(request));
        errorResponse.setFieldErrors(Stream.of(new FieldError(null, ex.getVariableName(), "Variável de URI obrigatória.")).collect(Collectors.toList()));
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = messageService.getMessage("recurso.requisicao-tipo-invalido");
        ErrorResponse errorResponse = new ErrorResponse(status, message, obterPath(request));
        errorResponse.setFieldErrors(Stream.of(obterErro(ex)).collect(Collectors.toList()));
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status, messageService.getMessage("recurso.requisicao-ilegivel"), obterPath(request));
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status, messageService.getMessage("recurso.requisicao-campo-obrigatorio"), obterPath(request));
        errorResponse.setFieldErrors(Stream.of(new FieldError(null, ex.getParameterName(), "Parametro de requisição obrigatório")).collect(Collectors.toList()));
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

//    protected ResponseEntity<Object> handleExceptionIntegration(IntegrationException ex, HttpStatus status, WebRequest request, String messageCode) {
//        ErrorResponseIntegration errorResponse = new ErrorResponseIntegration(status, messageService.getMessage(messageCode), obterPath(request), ex.getErrorIntegration());
//        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
//    }
    
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest req, BusinessException businessException) {
        String message = messageService.getMessage(businessException.key());
        String exceptionMessage = Objects.nonNull(ex.getMessage()) ? " " + ex.getMessage() : "";

        if (businessException.returnMessageException())
            message += exceptionMessage;

        ErrorResponse errorResponse = new ErrorResponse(businessException.status(), message, obterPath(req));
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), businessException.status(), req);

    }

    private List<FieldError> obterListaErros(BindingResult bindingResult) {
    	return bindingResult.getFieldErrors().stream()
                .map(f -> new FieldError(f.getObjectName(), f.getField(), messageService.getMessage(f)))
                .collect(Collectors.toList());
    }

    private String obterPath(WebRequest request) {
        return request instanceof ServletWebRequest ? ((ServletWebRequest) request).getRequest().getRequestURI()
                : request.getContextPath();
    }

    private FieldError obterErro(TypeMismatchException ex){
        String message = "O tipo do campo deve ser: ";
        if(ex instanceof MethodArgumentTypeMismatchException){
            return new FieldError(
                    null,
                    ((MethodArgumentTypeMismatchException) ex).getName(),
                    message + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        }else if(ex instanceof MethodArgumentConversionNotSupportedException){
            return new FieldError(
                    null,
                    ((MethodArgumentConversionNotSupportedException) ex).getName(),
                    message + Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        }
        return null;
    }
}
