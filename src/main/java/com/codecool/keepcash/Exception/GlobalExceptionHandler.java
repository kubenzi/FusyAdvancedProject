package com.codecool.keepcash.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IdNotFoundException.class, LoginNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(IdNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler({NewUserDataException.class})
    public ResponseEntity<String> wrongUserData(NewUserDataException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_ACCEPTABLE);
    }

    @ExceptionHandler({NewOperationDtoValidationException.class})
    public ResponseEntity<String> wrongNewOperationData(NewOperationDtoValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_ACCEPTABLE);
    }

    @ExceptionHandler({NewCategoryValidationException.class})
    public ResponseEntity<String> wrongNewCategoryData(NewCategoryValidationException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_ACCEPTABLE);
    }

}
