package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationTypeDtoToOperationConverter {

    public OperationTypeDtoToOperationConverter() {
    }

    public OperationType convertDtoToOperationType(OperationTypeDto operationTypeDto){
        return new OperationType(operationTypeDto.getName());
    }

    public List<OperationType> convertDtoToList(List<OperationTypeDto> operationTypes) {
        return operationTypes.stream()
                .map(this::convertDtoToOperationType)
                .collect(Collectors.toList());
    }
}
