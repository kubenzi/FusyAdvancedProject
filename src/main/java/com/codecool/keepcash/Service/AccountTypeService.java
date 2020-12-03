package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> getAllAccountTypes();
    void addAccountType(AccountTypeDto accountTypeDto);
    AccountTypeDto getAccountTypeById(Long id);
    void deleteAccountTypeById(Long id);
    void updateAccountType(Long id, AccountTypeDto accountTypeDto);

}
