package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountTypeToAccountTypeDtoConverter {

    public AccountTypeToAccountTypeDtoConverter() {
    }

    public AccountTypeDto convertToDto(AccountType accountType) {
        return new AccountTypeDto(accountType.getId(),
                accountType.getName());
    }

    public List<AccountTypeDto> convertListToDto(List<AccountType> accountTypeList) {
        return accountTypeList.stream().
                map(this::convertToDto).
                collect(Collectors.toList());
    }
}
