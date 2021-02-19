package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Dto.Bank.BankDto;
import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Service.Account.AccountTypeService;
import com.codecool.keepcash.Service.Bank.BankService;
import com.codecool.keepcash.Service.Currency.CurrencyService;
import com.codecool.keepcash.Service.Validation.NewOperationValidation.NewOperationDtoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UtilController {

    private AccountTypeService accountTypeService;
    private CurrencyService currencyService;
    private BankService bankService;
    private NewOperationDtoService operationDtoService;

    public UtilController(AccountTypeService accountTypeService,
                          CurrencyService currencyService,
                          BankService bankService,
                          NewOperationDtoService operationDtoService) {
        this.accountTypeService = accountTypeService;
        this.currencyService = currencyService;
        this.bankService = bankService;
        this.operationDtoService = operationDtoService;
    }

    @GetMapping("/account-types")
    @ResponseStatus(OK)
    public List<AccountTypeDto> getAccountTypes() {
        return accountTypeService.getAllAccountTypeDto();
    }

    @GetMapping("/currencies")
    @ResponseStatus(OK)
    public List<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/banks")
    @ResponseStatus(OK)
    public List<BankDto> getAllBanks() {
        return bankService.getAllBanks();
    }

}
