package com.codecool.keepcash.Exception;

public class NewUserDataException extends RuntimeException {

    public NewUserDataException(String getConcatenatedListOfErrors) {

        super("List of registration errors: " + getConcatenatedListOfErrors);
    }
}
