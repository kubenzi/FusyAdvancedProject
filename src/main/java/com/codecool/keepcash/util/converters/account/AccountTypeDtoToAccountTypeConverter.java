package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountTypeDtoToAccountTypeConverter {

    public AccountTypeDtoToAccountTypeConverter() {
    }

    public AccountType convertDtoToAccountType(AccountTypeDto accountTypeDto){
        return new AccountType(accountTypeDto.getName());
    }

    public List<AccountType> convertDtoToList(List<AccountTypeDto> accountTypeList) {
        return accountTypeList.stream().
                map(this::convertDtoToAccountType).
                collect(Collectors.toList());
    }
}
