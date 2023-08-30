package com.superapp.bill_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ValidatorServiceException extends RuntimeException{
    public ValidatorServiceException() {
        super();
    }
    public ValidatorServiceException(String message) {
        super(message);
    }

}
