package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationTypeToOperationTypeDtoConverter {

    public OperationTypeToOperationTypeDtoConverter() {
    }

    public static OperationTypeDto convertToDto(OperationType operationType) {
        return new OperationTypeDto(operationType.getId(),
                operationType.getName());
    }

    public static List<OperationTypeDto> convertListToDto(List<OperationType> operationTypes) {
        return operationTypes.stream()
                .map(operationType -> convertToDto(operationType))
                .collect(Collectors.toList());
    }
}
