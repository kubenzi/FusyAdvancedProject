package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;

import java.util.List;

public interface OperationService {

    List<OperationDto> getAllOperationsByUserId(Long userId);
    List<OperationDto> getAllOperationByCategoryId(Long categoryId);
    List<OperationDto> getAllOperationByAccountId(Long accountId);
    void deleteOperationsById(Long operationId);
    void addTransaction(NewOperationDto newOperationDto);
    List<OperationDto> findAllByUserIdAndPeriod(Long userId, Integer period);
    List<OperationDto> findAllByCategoryIdAndPeriod(Long categoryId, Integer period);
}
