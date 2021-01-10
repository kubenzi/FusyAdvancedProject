package com.codecool.keepcash.util.converters.operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Entity.OperationType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.OperationTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationDtoToOperationConverter {

    private OperationTypeDtoToOperationConverter operationTypeDtoToOperationConverter;
    private OperationTypeRepository operationTypeRepository;

    public OperationDtoToOperationConverter(OperationTypeDtoToOperationConverter operationTypeDtoToOperationConverter,
                                            OperationTypeRepository operationTypeRepository) {
        this.operationTypeDtoToOperationConverter = operationTypeDtoToOperationConverter;
        this.operationTypeRepository = operationTypeRepository;
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

    public Operation convertNewDtoToOperation(NewOperationDto newOperationDto){

        return new Operation(newOperationDto.getDescription(),
                newOperationDto.getValue(),
                newOperationDto.getDate(),
                operationTypeRepository.findById(newOperationDto.getOperationTypeId())
                        .orElseThrow(() -> new IdNotFoundException(
                                newOperationDto.getOperationTypeId(), OperationType.class.getSimpleName()))
                );
    }

}
