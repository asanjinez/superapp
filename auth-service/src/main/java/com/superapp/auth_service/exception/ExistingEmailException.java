package com.superapp.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingEmailException extends RuntimeException{
    public ExistingEmailException() {
        super();
    }
    public ExistingEmailException(String message) {
        super(message);
    }

}
