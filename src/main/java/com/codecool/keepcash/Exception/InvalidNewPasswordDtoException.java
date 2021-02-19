package com.codecool.keepcash.Exception;

public class InvalidNewPasswordDtoException extends RuntimeException {

    public InvalidNewPasswordDtoException(String concatenatedListOfErrors) {
        super("List of new password dto errors: " + concatenatedListOfErrors);
    }
}
