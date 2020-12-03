package com.codecool.keepcash.util;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;
import org.springframework.stereotype.Component;

@Component
public class AccountTypeDtoToAccountTypeConverter {

    public AccountTypeDtoToAccountTypeConverter() {
    }

    public AccountType convertDtoToAccountType(AccountTypeDto accountTypeDto){
        return new AccountType(accountTypeDto.getName());
    }
}
