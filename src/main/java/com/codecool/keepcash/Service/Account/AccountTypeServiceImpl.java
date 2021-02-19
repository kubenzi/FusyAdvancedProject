package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.util.AccountTypeFields;
import com.codecool.keepcash.util.converters.account.AccountTypeToAccountTypeDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository accountTypeRepository;

    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public AccountType getAccountTypeById(Long id) {
        Optional<AccountType> accountType = accountTypeRepository.findById(id);

        if (accountType.isPresent()) {
            return accountType.get();
        } else {
            throw new IdNotFoundException(id, AccountType.class.getSimpleName());
        }
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypeDto() {
        return AccountTypeToAccountTypeDtoConverter
                .convertListToDto((List<AccountType>) accountTypeRepository.findAll());
    }

    @Override
    public AccountTypeDto getAccountTypeDtoById(Long id) {
        return AccountTypeToAccountTypeDtoConverter.convertToDto(getAccountTypeById(id));
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypesSortByName(String sortByName) {
        List<AccountTypeDto> allAccountTypes = getAllAccountTypeDto();
        if (sortByName.equals(AccountTypeFields.NAME.name())) {
            return allAccountTypes.stream()
                    .sorted((account1, account2) -> account1.getName().compareTo(account2.getName()))
                    .collect(Collectors.toList());
        }

        return allAccountTypes;
    }

    @Override
    public void deleteAccountTypeById(Long id) {
        try {
            accountTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, AccountType.class.getSimpleName());
        }
    }
}

