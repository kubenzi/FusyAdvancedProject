package com.codecool.keepcash.Exception;

public class NewOperationDtoValidationException extends RuntimeException {
    public NewOperationDtoValidationException(String message) {
        super("Invalid input because of: " + message);
    }
}
