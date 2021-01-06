package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.OperationDto;

import java.util.List;

public interface OperationService {
    List<OperationDto> getAllOperations();
}
