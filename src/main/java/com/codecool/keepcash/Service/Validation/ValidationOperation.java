package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;

import static com.codecool.keepcash.Service.Validation.ValidationError.AT_LEAST_ONE_VALUE_IS_NULL;

public class ValidationOperation {

    public Validator operationDtoValidation(NewOperationDto newOperationDto) {


        return Validator.of(newOperationDto)
                .validate(form -> form.getDescription()
                                != null && !form.getDescription().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getAccountId() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getCategoryId() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getValue() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getDate() != null, AT_LEAST_ONE_VALUE_IS_NULL);

    }

}
