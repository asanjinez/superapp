package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoCartFoundException extends RuntimeException{
    public NoCartFoundException() {
        super();
    }
    public NoCartFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public NoCartFoundException(String message) {
        super(message);
    }

    public NoCartFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCartFoundException(Throwable cause) {
        super(cause);
    }
}
