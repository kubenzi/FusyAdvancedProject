package com.codecool.keepcash.util;

import com.codecool.keepcash.Dto.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationToOperationDtoConverter {

    public OperationToOperationDtoConverter() {
    }

    public OperationDto convertToDto(Operation operation) {
        return new OperationDto(operation.getId(),
                operation.getDescription(),
                operation.getValue());
    }

    public List<OperationDto> convertListToDto(List<Operation> operations) {
        return operations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
