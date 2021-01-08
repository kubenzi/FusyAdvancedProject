package com.codecool.keepcash.Exception;

public class IdNotFoundException extends RuntimeException {


    public IdNotFoundException(Long id, String className) {
        super("Id= " + id + " not found for " + className);
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
