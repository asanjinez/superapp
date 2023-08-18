package com.superapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidBillException extends RuntimeException{
    public InvalidBillException() {
        super();
    }
    public InvalidBillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public InvalidBillException(String message) {
        super(message);
    }

    public InvalidBillException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBillException(Throwable cause) {
        super(cause);
    }
}
