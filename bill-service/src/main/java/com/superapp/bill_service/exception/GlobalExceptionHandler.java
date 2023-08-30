package com.superapp.bill_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler  {
    // Specific Exceptions
    @ExceptionHandler(NoBillFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(NoBillFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingIdException.class)
    public ResponseEntity<?> handleExistingId(ExistingIdException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBillException.class)
    public ResponseEntity<?> handleInvalidBillException(InvalidBillException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidatorServiceException.class)
    public ResponseEntity<?> handleValidatorServiceException(ValidatorServiceException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unknown error :(", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
