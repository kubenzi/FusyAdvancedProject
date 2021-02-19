package com.codecool.keepcash.Exception;

public class InvalidNewAccountDtoException extends RuntimeException {

    public InvalidNewAccountDtoException(String concatenatedListOfErrors) {
        super("List of new account dto errors: " + concatenatedListOfErrors);
    }
}
