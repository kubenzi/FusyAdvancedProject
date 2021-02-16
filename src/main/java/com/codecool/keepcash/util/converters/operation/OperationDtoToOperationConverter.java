package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class OperationDtoToOperationConverter {

    public OperationDtoToOperationConverter() {
    }

    public static Operation convertDtoToOperation(OperationDto operationDto) {
        return new Operation(operationDto.getDescription(),
                operationDto.getValue(),
                operationDto.getDate());
    }

    public static List<Operation> convertDtoListToOperationList(List<OperationDto> operations) {
        return operations.stream()
                .map(operationDto -> convertDtoToOperation(operationDto))
                .collect(Collectors.toList());
    }

    public static Operation convertNewDtoToOperation(NewOperationDto newOperationDto) {

        return new Operation(newOperationDto.getDescription(),
                newOperationDto.getValue(),
                newOperationDto.getDate()
        );
    }

}
