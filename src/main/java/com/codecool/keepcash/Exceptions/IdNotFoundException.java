package com.codecool.keepcash.Exceptions;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(Long id) {
        super("ID: " + id + " DOESN'T EXIST");
    }

    public IdNotFoundException() {
    }
}
