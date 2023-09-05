package com.superapp.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingUsernameException extends RuntimeException{
    public ExistingUsernameException() {
        super();
    }
    public ExistingUsernameException(String message) {
        super(message);
    }

}
