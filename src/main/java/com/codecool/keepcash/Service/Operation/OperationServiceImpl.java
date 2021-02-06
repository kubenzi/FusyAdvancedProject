package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.OperationRepository;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private AccountService accountService;
    private CategoryService categoryService;


    public OperationServiceImpl(OperationRepository operationRepository,
                                AccountService accountService,
                                CategoryService categoryService) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
        this.categoryService = categoryService;
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
    @Transactional
    public void addTransaction(NewOperationDto newOperationDto) {
        newOperationDto.setDate(new Date(System.currentTimeMillis()));

        Operation newOperation = operationRepository.save(
                OperationDtoToOperationConverter
                        .convertNewDtoToOperation(newOperationDto)
        );

        Account account = accountService.getAccountById(newOperationDto.getAccountId());
        account.getOperations().add(newOperation);
        accountService.saveUpdatedAccount(account);

        Category category = categoryService.getCategoryById(newOperationDto.getCategoryId());
        category.getOperations().add(newOperation);
        categoryService.saveUpdatedCategory(category);
    }

    @Override
    public void addNewCSVOperations(List<Operation> csvOperations, Long userId, Long accountId) {
        Account account = accountService.getAccountById(accountId);
        Category catIncome = categoryService.getCategoryByNameAndUserId("INCOME", userId);
        Category catUnassigned = categoryService.getCategoryByNameAndUserId("UNASSIGNED", userId);

        for (Operation operation : csvOperations) {
            account.getOperations().add(operation);

            if (operation.getValue() > 0) {
                catIncome.getOperations().add(operation);
            } else {
                catUnassigned.getOperations().add(operation);
            }
        }

        accountService.saveUpdatedAccount(account);
        categoryService.saveUpdatedCategory(catIncome);
        categoryService.saveUpdatedCategory(catUnassigned);
    }

    @Override
    public void deleteOperationsById(Long operationId) {
        try {
            this.operationRepository.deleteById(operationId);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(operationId, AccountType.class.getSimpleName());
        }
    }

    @Override
    public List<OperationDto> findAllByCategoryIdAndPeriod(Long categoryId, Integer period) {
        return OperationToOperationDtoConverter.convertListToDto(
                operationRepository.findAllByCategoryAndPeriod(categoryId, LocalDate.now().minusDays(period)));
    }

    @Override
    public List<OperationDto> findAllByUserIdAndPeriod(Long userId, Integer period) {

        return OperationToOperationDtoConverter.convertListToDto(
                operationRepository.findAllByUserIdAndPeriod(userId, LocalDate.now().minusDays(period)));
    }
}
