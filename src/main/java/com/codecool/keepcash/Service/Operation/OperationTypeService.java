package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Entity.OperationType;

import java.util.List;

public interface OperationTypeService {
    OperationType findOperationTypeById(Long operationTypeId);
    List<OperationTypeDto> getAllOperationTypes();
}
