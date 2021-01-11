package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> getAllAccountTypes();

    List<AccountTypeDto> getAllAccountTypesSortByName(String sortByName);

    void addAccountType(AccountTypeDto accountTypeDto);

    AccountTypeDto getAccountTypeById(Long id);

    void deleteAccountTypeById(Long id);

    void updateAccountType(Long id, AccountTypeDto accountTypeDto);

}
