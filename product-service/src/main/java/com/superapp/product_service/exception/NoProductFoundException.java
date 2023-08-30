package com.superapp.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException() {
        super();
    }
    public NoProductFoundException(String message) {
        super(message);
    }

}
