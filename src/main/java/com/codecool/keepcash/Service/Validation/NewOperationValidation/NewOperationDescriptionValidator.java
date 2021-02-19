package com.codecool.keepcash.Service.Validation.NewOperationValidation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Service.Validation.ValidationError;

import java.util.List;

import static com.codecool.keepcash.Service.Validation.ValidationError.TO_LONG_DESCRIPTION;

public class NewOperationDescriptionValidator extends ValidatorChain {

    public NewOperationDescriptionValidator(ValidatorChain nextValidatorChain) {
        super(nextValidatorChain);
    }

    @Override
    List<ValidationError> validate(NewOperationDto newOperationDto, List<ValidationError> validationResult) {
        if (newOperationDto.getDescription() != null) {
            if (newOperationDto.getDescription().length() > 200) {
                validationResult.add(TO_LONG_DESCRIPTION);
            }
        }

        if (nextValidatorChain != null) {
            return nextValidatorChain.validate(newOperationDto, validationResult);
        }

        return validationResult;
    }
}
