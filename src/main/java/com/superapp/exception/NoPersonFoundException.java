package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NoPersonFoundException extends RuntimeException{
    public NoPersonFoundException() {
        super();
    }
    public NoPersonFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public NoPersonFoundException(String message) {
        super(message);
    }

    public NoPersonFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPersonFoundException(Throwable cause) {
        super(cause);
    }
}
