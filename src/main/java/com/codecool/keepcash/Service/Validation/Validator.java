package com.codecool.keepcash.Service.Validation;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Validator<T> {

    private T formToValidationData;
    private List<ValidationError> validationErrors = new ArrayList<>();

    public Validator(T formToValidationData) {
        this.formToValidationData = formToValidationData;
    }

    public static <T> Validator<T> of(T t) {
        return new Validator<>(t);
    }


    public Validator<T> validate(Predicate<T> validation, ValidationError validationError){
        if(!validation.test(formToValidationData)){
            validationErrors.add(validationError);
        }
        return this;
    }

    public boolean result() {
        return this.validationErrors.size() == 0;
    }

    public List<ValidationError> getErrors() {
        return validationErrors;
    }

}
