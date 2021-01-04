package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationDtoToOperationConverter {

    private OperationTypeDtoToOperationConverter operationTypeDtoToOperationConverter;

    public OperationDtoToOperationConverter(OperationTypeDtoToOperationConverter operationTypeDtoToOperationConverter) {
        this.operationTypeDtoToOperationConverter = operationTypeDtoToOperationConverter;
    }

    public Operation convertDtoToOperation(OperationDto operationDto){
        return new Operation(operationDto.getDescription(),
                operationDto.getValue(),
                operationDto.getDate(),
                operationTypeDtoToOperationConverter.convertDtoToOperationType(operationDto.getOperationType()));
    }

    public List<Operation> convertDtoListToOperationList(List<OperationDto> operations) {
        return operations.stream()
                .map(this::convertDtoToOperation)
                .collect(Collectors.toList());
    }

}
