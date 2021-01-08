package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Account.AccountTypeService;
import com.codecool.keepcash.Service.Currency.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    public AccountService accountService;
    public CurrencyService currencyService;
    public AccountTypeService accountTypeService;

    public AccountsController(AccountService accountService,
                              CurrencyService currencyService,
                              AccountTypeService accountTypeService) {
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.accountTypeService = accountTypeService;
    }

    @GetMapping("/users/{userId}/accounts")
    @ResponseStatus(OK)
    public List<AccountDto> getAccountsByUserId(@PathVariable Long userId) {
        return accountService.getAccountsByUserId(userId);
    }

//    @GetMapping("/users/{userId}/accounts")
//    @ResponseStatus(OK)
//    public List<CurrencyDto> getCurrenciesList() {
//        return currencyService.getAllCurrencies();
//    }
//
//    @GetMapping("/users/{userId}/accounts")
//    @ResponseStatus(OK)
//    public List<AccountTypeDto> getAccountTypes() {
//        return accountTypeService.getAllAccountTypes();
//    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    @ResponseStatus(OK)
    public AccountDto getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping("/users/{userId}/accounts")
    @ResponseStatus(CREATED)
    public void addNewAccountForUserId(@PathVariable Long userId,
                                       @RequestBody NewAccountDto newAccountDto) {
        accountService.createNewAccount(newAccountDto, userId);
    }

    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAccountById(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);
    }

}
