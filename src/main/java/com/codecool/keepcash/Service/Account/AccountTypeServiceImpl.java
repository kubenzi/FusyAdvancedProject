package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.util.AccountTypeFields;
import com.codecool.keepcash.util.converters.account.AccountTypeDtoToAccountTypeConverter;
import com.codecool.keepcash.util.converters.account.AccountTypeToAccountTypeDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository accountTypeRepository;


    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {
        return AccountTypeToAccountTypeDtoConverter
                .convertListToDto((List<AccountType>) accountTypeRepository.findAll());
    }

    @Override
    public void addAccountType(AccountTypeDto accountTypeDto) {
        accountTypeRepository.save(AccountTypeDtoToAccountTypeConverter.convertDtoToAccountType(accountTypeDto));
    }

    @Override
    public AccountTypeDto getAccountTypeById(Long id) {
        return AccountTypeToAccountTypeDtoConverter.convertToDto(
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
        AccountType accountType = AccountTypeDtoToAccountTypeConverter.
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

