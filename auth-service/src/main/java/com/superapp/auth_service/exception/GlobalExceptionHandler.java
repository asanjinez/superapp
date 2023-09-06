package com.superapp.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler  {
    // Specific Exceptions
    @ExceptionHandler(ExistingIdException.class)
    public ResponseEntity<?> handleExistingId(ExistingIdException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingUsernameException.class)
    public ResponseEntity<?> handleExistingUsername(ExistingUsernameException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<?> handleExistingId(ExistingEmailException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> InvalidCredentialsException(InvalidCredentialsException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    // Global Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unknown error :(", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
