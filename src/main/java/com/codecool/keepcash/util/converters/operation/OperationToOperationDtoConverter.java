package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class OperationToOperationDtoConverter {

    public OperationToOperationDtoConverter() {
    }

    public static OperationDto convertToDto(Operation operation) {
        return new OperationDto(operation.getId(),
                operation.getDescription(),
                operation.getValue(),
                operation.getDate(),
                OperationTypeToOperationTypeDtoConverter.convertToDto(operation.getOperationType()));
    }

    public static List<OperationDto> convertListToDto(List<Operation> operations) {
        return operations.stream()
                .map(operation -> convertToDto(operation))
                .collect(Collectors.toList());
    }
}
