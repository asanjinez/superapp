package com.superapp.bill_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoBillFoundException extends RuntimeException{
    public NoBillFoundException() {
        super();
    }
    public NoBillFoundException(String message) {
        super(message);
    }

}
