package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;

import java.util.List;

public interface OperationService {

    List<OperationDto> getAllOperationsByUserId(Long userId);
    List<OperationDto> getAllOperationByCategoryId(Long categoryId);
    List<OperationDto> getAllOperationByAccountId(Long accountId);
    void addTransaction(NewOperationDto newOperationDto, Long userId);
    void addNewCSVOperations(List<Operation> csvOperations, Long userId, Long accountId);
    void deleteOperationsById(Long operationId);
    List<OperationDto> findAllByUserIdAndPeriod(Long userId, Integer period);
    List<OperationDto> findAllByCategoryIdAndPeriod(Long categoryId, Integer period);
    List<OperationDto> findAllByAccountIdAndPeriod(Long categoryId, Integer period);

    List<OperationDto> getLastOperation(Long userId, Integer lastOperation);
}
