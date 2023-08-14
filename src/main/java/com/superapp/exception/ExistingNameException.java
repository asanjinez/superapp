package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ExistingNameException extends RuntimeException{
    public ExistingNameException() {
        super();
    }
    public ExistingNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ExistingNameException(String message) {
        super(message);
    }

    public ExistingNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingNameException(Throwable cause) {
        super(cause);
    }
}
