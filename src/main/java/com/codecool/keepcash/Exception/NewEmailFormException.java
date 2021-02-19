package com.codecool.keepcash.Exception;

public class NewEmailFormException extends RuntimeException {

    public NewEmailFormException(String concatenatedListOfErors) {
        super("List of new email form errors: " + concatenatedListOfErors);
    }
}
