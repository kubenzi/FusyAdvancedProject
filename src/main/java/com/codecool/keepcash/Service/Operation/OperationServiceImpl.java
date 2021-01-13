package com.codecool.keepcash.Service.Operation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountRepository;
import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.Repository.OperationRepository;
import com.codecool.keepcash.Repository.OperationTypeRepository;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private OperationRepository operationRepository;
    private AccountRepository accountRepository;
    private CategoryRepository categoryRepository;
    private OperationTypeRepository operationTypeRepository;


    public OperationServiceImpl(OperationRepository operationRepository,
                                AccountRepository accountRepository,
                                CategoryRepository categoryRepository,
                                OperationTypeRepository operationTypeRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationTypeRepository = operationTypeRepository;
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
//    @Transactional ??
    public void addTransaction(NewOperationDto newOperationDto) {
        Operation newOperation = operationRepository.save(
                OperationDtoToOperationConverter.convertNewDtoToOperation(newOperationDto, operationTypeRepository)
        );
        Account account = accountRepository.findById(newOperationDto.getAccountId())
                .orElseThrow(() -> new IdNotFoundException(
                        newOperationDto.getAccountId(), Account.class.getSimpleName())
                );
        account.getOperations().add(newOperation);
        accountRepository.save(account);

        Category category = categoryRepository.findById(newOperationDto.getCategoryId())
                .orElseThrow(() -> new IdNotFoundException(
                        newOperationDto.getCategoryId(), Category.class.getSimpleName()
                ));

        category.getOperations().add(newOperation);
        categoryRepository.save(category);
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
}
