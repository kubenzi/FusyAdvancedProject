package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.*;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.OperationRepository;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private AccountService accountService;
    private CategoryService categoryService;
    private OperationTypeService operationTypeService;


    public OperationServiceImpl(OperationRepository operationRepository,
                                AccountService accountService,
                                CategoryService categoryService,
                                OperationTypeService operationTypeService) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.operationTypeService = operationTypeService;
    }

    @Override
    @Transactional
    public void addTransaction(NewOperationDto newOperationDto) {
        newOperationDto.setDate(new Date(System.currentTimeMillis()));

        OperationType operationType = operationTypeService.findOperationTypeById(newOperationDto.getOperationTypeId());
        Operation newOperation = operationRepository.save(
                OperationDtoToOperationConverter
                        .convertNewDtoToOperation(newOperationDto, operationType)
        );

        Account account = accountService.getAccountById(newOperationDto.getAccountId());
        account.getOperations().add(newOperation);
        accountService.saveUpdatedAccount(account);

        Category category = categoryService.getCategoryById(newOperationDto.getCategoryId());
        category.getOperations().add(newOperation);
        categoryService.saveUpdatedCategory(category);
    }


    @Override
    public List<OperationDto> getAllOperationsByUserId(Long userId) {
        List<Operation> allOperations = operationRepository.findAllByUserId(userId);
        return OperationToOperationDtoConverter.convertListToDto(allOperations);
    }

    @Override
    public List<OperationDto> getAllOperationByCategoryId(Long categoryId) {
        return OperationToOperationDtoConverter.convertListToDto(operationRepository.findByCategoryId(categoryId));
    }

    @Override
    public List<OperationDto> getAllOperationByAccountId(Long accountId) {
        return OperationToOperationDtoConverter.convertListToDto(operationRepository.findByAccountId(accountId));
    }

    @Override
    public void deleteOperationsById(Long operationId) {
        try {
            this.operationRepository.deleteById(operationId);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(operationId, AccountType.class.getSimpleName());
        }
    }
}
