package com.codecool.keepcash.Service.Validation.NewOperationValidation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Service.Validation.ValidationError;

import java.util.List;

public abstract class ValidatorChain {

    protected ValidatorChain nextValidatorChain;

    public ValidatorChain(ValidatorChain nextValidatorChain) {
        this.nextValidatorChain = nextValidatorChain;
    }

    abstract List<ValidationError> validate(NewOperationDto newOperationDto, List<ValidationError> validationResult);
}
