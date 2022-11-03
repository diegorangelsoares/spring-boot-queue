package com.diego.cadastro.services;

import org.springframework.validation.FieldError;

import javax.validation.Path;

public interface MessageService {

    String getMessage(String key);

    String getMessage(FieldError fieldError);

    String getMessage(Path path);

}
