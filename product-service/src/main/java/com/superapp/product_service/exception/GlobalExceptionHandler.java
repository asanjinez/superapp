package com.superapp.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler  {
    // Specific Exceptions
    @ExceptionHandler(NoProductFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(NoProductFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingIdException.class)
    public ResponseEntity<?> handleExistingId(ExistingIdException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unknown error :(", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
