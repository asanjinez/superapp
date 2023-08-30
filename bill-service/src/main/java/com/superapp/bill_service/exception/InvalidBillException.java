package com.superapp.bill_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidBillException extends RuntimeException{
    public InvalidBillException() {
        super();
    }
    public InvalidBillException(String message) {
        super(message);
    }

}
