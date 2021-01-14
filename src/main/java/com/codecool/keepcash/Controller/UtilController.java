package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Dto.Operation.OperationTypeDto;
import com.codecool.keepcash.Service.Account.AccountTypeService;
import com.codecool.keepcash.Service.Currency.CurrencyService;
import com.codecool.keepcash.Service.Operation.OperationTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class UtilController {

    private AccountTypeService accountTypeService;
    private CurrencyService currencyService;
    private OperationTypeService operationTypeService;

    public UtilController(AccountTypeService accountTypeService,
                          CurrencyService currencyService,
                          OperationTypeService operationTypeService) {
        this.accountTypeService = accountTypeService;
        this.currencyService = currencyService;
        this.operationTypeService = operationTypeService;
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

    @GetMapping("/operation-types")
    @ResponseStatus(OK)
    public List<OperationTypeDto> getAllOperationTypes() {
        return operationTypeService.getAllOperationTypesDto();
    }
}
