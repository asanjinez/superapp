package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingIdException extends RuntimeException{
    public ExistingIdException() {
        super();
    }
    public ExistingIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ExistingIdException(String message) {
        super(message);
    }

    public ExistingIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingIdException(Throwable cause) {
        super(cause);
    }
}
