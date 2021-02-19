package com.codecool.keepcash.Exception;

public class BuildCategoryDeleteException extends RuntimeException {
    public BuildCategoryDeleteException(Long categoryId) {
        super("Category with id " + categoryId +" can't be delete, because is build-in");
    }
}
