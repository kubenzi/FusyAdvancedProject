package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;

import java.util.List;

public interface AccountService {

    void createNewAccount(NewAccountDto newAccountDto, Long userId);
    AccountDto getAccountById(Long accountId);
    List<AccountDto> getAccountsByUserId(Long userId);
    void deleteAccountById(Long accountId);
}
