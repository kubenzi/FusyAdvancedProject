package com.codecool.keepcash.Service.Validation.NewOperationValidation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Service.Validation.ValidationError;

import java.util.List;

import static com.codecool.keepcash.Service.Validation.ValidationError.AT_LEAST_ONE_VALUE_IS_NULL;

public class NewOperationNullValidator extends ValidatorChain {

    public NewOperationNullValidator(ValidatorChain nextValidatorChain) {
        super(nextValidatorChain);
    }

    @Override
    List<ValidationError> validate(NewOperationDto newOperationDto, List<ValidationError> validationResult) {
        if (newOperationDto.getAccountId() == null) {
            validationResult.add(AT_LEAST_ONE_VALUE_IS_NULL);
        }
        if (newOperationDto.getCategoryId() == null) {
            validationResult.add(AT_LEAST_ONE_VALUE_IS_NULL);
        }
        if (newOperationDto.getValue() == null) {
            validationResult.add(AT_LEAST_ONE_VALUE_IS_NULL);
        }
        if (newOperationDto.getDate() == null) {
            validationResult.add(AT_LEAST_ONE_VALUE_IS_NULL);
        }
        if (newOperationDto.getDescription() == null || newOperationDto
                .getDescription().equalsIgnoreCase("null")) {
            validationResult.add(AT_LEAST_ONE_VALUE_IS_NULL);
        }
        if (nextValidatorChain != null) {
            return nextValidatorChain.validate(newOperationDto, validationResult);
        }
        return validationResult;
    }
}
