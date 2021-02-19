package com.codecool.keepcash.Exception;

public class InvalidNewEmailDtoException extends RuntimeException {

    public InvalidNewEmailDtoException(String concatenatedListOfErrors) {
        super("List of new email dto errors: " + concatenatedListOfErrors);
    }
}
