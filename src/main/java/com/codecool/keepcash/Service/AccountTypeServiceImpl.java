package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.util.AccountTypeDtoToAccountTypeConverter;
import com.codecool.keepcash.util.AccountTypeToAccountTypeDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    AccountTypeRepository accountTypeRepository;
    AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter;
    AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter;


    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository,
                                  AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter,
                                  AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeToAccountTypeDtoConverter = accountTypeToAccountTypeDtoConverter;
        this.accountTypeDtoToAccountTypeConverter = accountTypeDtoToAccountTypeConverter;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        return accountTypeToAccountTypeDtoConverter.convertListToDto((List<AccountType>) accountTypeRepository.findAll());
    }

    @Override
    public void addAccountType(AccountTypeDto accountTypeDto) {
        accountTypeRepository.save(accountTypeDtoToAccountTypeConverter.convertDtoToAccountType(accountTypeDto));
    }

    @Override
    public AccountTypeDto getAccountTypeById(Long id) {
        return accountTypeToAccountTypeDtoConverter.convertToDto(
                accountTypeRepository.findById(id)
                        .orElseThrow(() -> new IdNotFoundException(
                                id, AccountType.class.getSimpleName())
                        ));
    }

    @Override
    public void deleteAccountTypeById(Long id) {
        try {
            accountTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, AccountType.class.getSimpleName());
        }
    }

    @Override
    public void updateAccountType(Long id, AccountTypeDto accountTypeDto) {
        AccountType accountType = accountTypeDtoToAccountTypeConverter.
                convertDtoToAccountType(accountTypeDto);
        accountType.setId(id);
        accountTypeRepository.save(accountType);
    }
}

