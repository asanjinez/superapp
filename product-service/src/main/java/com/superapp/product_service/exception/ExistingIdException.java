package com.superapp.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingIdException extends RuntimeException{
    public ExistingIdException() {
        super();
    }
    public ExistingIdException(String message) {
        super(message);
    }

}
