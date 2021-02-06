package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;

import java.util.List;

public interface AccountService {

    Account getAccountById(Long id);
    AccountDto getAccountDtoById(Long accountId);
    List<AccountDto> getAccountsDtoByUserId(Long userId);
    Account createBuiltinAccounts();
    void addNewAccount(NewAccountDto newAccountDto, Long userId);
    void saveUpdatedAccount(Account account);
    void deleteAccountById(Long accountId);
}
