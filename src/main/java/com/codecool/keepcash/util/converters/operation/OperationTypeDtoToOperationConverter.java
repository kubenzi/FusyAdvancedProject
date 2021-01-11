package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class OperationTypeDtoToOperationConverter {

    public OperationTypeDtoToOperationConverter() {
    }

    public static OperationType convertDtoToOperationType(OperationTypeDto operationTypeDto) {
        return new OperationType(operationTypeDto.getName());
    }

    public static List<OperationType> convertDtoToList(List<OperationTypeDto> operationTypes) {
        return operationTypes.stream()
                .map(operationTypeDto -> convertDtoToOperationType(operationTypeDto))
                .collect(Collectors.toList());
    }
}
