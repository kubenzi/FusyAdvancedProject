package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;
import com.codecool.keepcash.Repository.OperationTypeRepository;
import com.codecool.keepcash.util.converters.operation.OperationTypeToOperationTypeDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationTypeServiceImpl implements OperationTypeService {

    private OperationTypeRepository operationTypeRepository;

    public OperationTypeServiceImpl(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    @Override
    public List<OperationTypeDto> getAllOperationTypes() {
        return OperationTypeToOperationTypeDtoConverter
                .convertListToDto((List<OperationType>) operationTypeRepository.findAll());
    }
}
