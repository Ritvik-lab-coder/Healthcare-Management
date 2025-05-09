package com.healthcaremanagement.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handleEmailAlreadyInUseException(EmailAlreadyInUseException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class) 
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class) 
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
