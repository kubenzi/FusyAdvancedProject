package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.util.converters.account.AccountTypeDtoToAccountTypeConverter;
import com.codecool.keepcash.util.AccountTypeFields;
import com.codecool.keepcash.util.converters.account.AccountTypeToAccountTypeDtoConverter;
import com.codecool.keepcash.util.NameComparator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private NameComparator nameComparator;
    private AccountTypeRepository accountTypeRepository;
    private AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter;
    private AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter;


    public AccountTypeServiceImpl(NameComparator nameComparator, AccountTypeRepository accountTypeRepository,
                                  AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter,
                                  AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter) {
        this.nameComparator = nameComparator;
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

    @Override
    public List<AccountTypeDto> getAllAccountTypesSortByName(String sortByName) {
        List<AccountTypeDto> allAccountTypes = getAllAccountTypes();
        if (sortByName.equals(AccountTypeFields.NAME.name())) {
            return allAccountTypes.stream()
                    .sorted((account1, account2) -> account1.getName().compareTo(account2.getName()))
                    .collect(Collectors.toList());
        }
        return allAccountTypes;

    }
}

