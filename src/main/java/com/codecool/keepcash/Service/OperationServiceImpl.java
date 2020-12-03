package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Repository.OperationRepository;
import com.codecool.keepcash.util.OperationToOperationDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private OperationToOperationDtoConverter operationToOperationDtoConverter;

    public OperationServiceImpl(OperationRepository operationRepository,
                                OperationToOperationDtoConverter operationToOperationDtoConverter) {
        this.operationRepository = operationRepository;
        this.operationToOperationDtoConverter = operationToOperationDtoConverter;
    }

    @Override
    public List<OperationDto> getAllOperations() {
        List<Operation> allOperations = (List<Operation>) operationRepository.findAll();

        return operationToOperationDtoConverter.convertListToDto(allOperations);
    };
}
