package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.OperationTypeRepository;
import com.codecool.keepcash.util.converters.operation.OperationTypeToOperationTypeDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {

    private OperationTypeRepository operationTypeRepository;

    public OperationTypeServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public OperationType findOperationTypeById(Long operationTypeId) {
        Optional<OperationType> operationType = operationTypeRepository.findById(operationTypeId);

        if (operationType.isPresent()) {
            return operationType.get();
        } else {
            throw new IdNotFoundException(operationTypeId, OperationType.class.getSimpleName());
        }
    }

    @Override
    public List<OperationTypeDto> getAllOperationTypesDto() {
        return OperationTypeToOperationTypeDtoConverter
                .convertListToDto((List<OperationType>) operationTypeRepository.findAll());
    }
}
