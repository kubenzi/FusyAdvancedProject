package com.codecool.keepcash.Exception;

public class NewCategoryValidationException extends RuntimeException{
    public NewCategoryValidationException(String message) {
        super("Invalid input because of: " + message);
    }
}
