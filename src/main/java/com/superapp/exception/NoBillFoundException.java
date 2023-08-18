package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoBillFoundException extends RuntimeException{
    public NoBillFoundException() {
        super();
    }
    public NoBillFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public NoBillFoundException(String message) {
        super(message);
    }

    public NoBillFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBillFoundException(Throwable cause) {
        super(cause);
    }
}
