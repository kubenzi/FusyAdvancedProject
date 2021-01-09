package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;

import java.util.List;

public interface OperationService {
    List<OperationDto> getAllOperations();

    List<Operation> getAllOperationsById(Long categoryId);
}
