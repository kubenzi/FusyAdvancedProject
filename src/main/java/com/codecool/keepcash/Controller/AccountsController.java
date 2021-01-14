package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Service.Account.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    private AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/{userId}/accounts")
    @ResponseStatus(OK)
    public List<AccountDto> getAccountsByUserId(@PathVariable Long userId) {
        return accountService.getAccountsDtoByUserId(userId);
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    @ResponseStatus(OK)
    public AccountDto getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountDtoById(accountId);
    }

    @PostMapping("/users/{userId}/accounts")
    @ResponseStatus(CREATED)
    public void addNewAccountForUserId(@PathVariable Long userId,
                                       @RequestBody NewAccountDto newAccountDto) {
        accountService.addNewAccount(newAccountDto, userId);
    }

    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteAccountById(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);
    }

}
