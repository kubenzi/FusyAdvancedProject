package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Entity.AccountType;

import java.util.List;

public interface AccountTypeService {

    AccountType getAccountTypeById(Long id);

    AccountTypeDto getAccountTypeDtoById(Long id);

    List<AccountTypeDto> getAllAccountTypeDto();

    List<AccountTypeDto> getAllAccountTypesSortByName(String sortByName);

    void deleteAccountTypeById(Long id);

}
