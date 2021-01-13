package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Entity.OperationType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.OperationTypeRepository;
import com.codecool.keepcash.Service.Operation.OperationTypeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public final class OperationDtoToOperationConverter {

    private OperationTypeService operationTypeService;

    public OperationDtoToOperationConverter(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }

    public static Operation convertDtoToOperation(OperationDto operationDto) {
        return new Operation(operationDto.getDescription(),
                operationDto.getValue(),
                operationDto.getDate(),
                OperationTypeDtoToOperationConverter.convertDtoToOperationType(operationDto.getOperationType()));
    }

    public static List<Operation> convertDtoListToOperationList(List<OperationDto> operations) {
        return operations.stream()
                .map(operationDto -> convertDtoToOperation(operationDto))
                .collect(Collectors.toList());
    }

    public static Operation convertNewDtoToOperation(NewOperationDto newOperationDto, OperationType operationType) {

        return new Operation(newOperationDto.getDescription(),
                newOperationDto.getValue(),
                newOperationDto.getDate(),
                operationType
        );
    }

}
