package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.AccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.util.converters.currency.CurrencyDtoToCurrencyConverter;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountDtoToAccountConverter {

    AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter;
    CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter;
    OperationDtoToOperationConverter operationDtoToOperationConverter;

    public AccountDtoToAccountConverter(AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter,
                                        CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter,
                                        OperationDtoToOperationConverter operationDtoToOperationConverter) {
        this.accountTypeDtoToAccountTypeConverter = accountTypeDtoToAccountTypeConverter;
        this.currencyDtoToCurrencyConverter = currencyDtoToCurrencyConverter;
        this.operationDtoToOperationConverter = operationDtoToOperationConverter;
    }

    public Account convertDtoToAccount(AccountDto accountDto){
        return new Account(accountDto.getName(),
                accountDto.getBalance(),
                accountDto.getAccountNumber(),
                accountTypeDtoToAccountTypeConverter.convertDtoToAccountType(accountDto.getAccountType()),
                currencyDtoToCurrencyConverter.convertDtoToCurrency(accountDto.getCurrency()),
                operationDtoToOperationConverter.convertDtoListToOperationList(accountDto.getOperations()));
    }

    public List<Account> convertDtoToList(List<AccountDto> accounts) {
        return accounts.stream().
                map(this::convertDtoToAccount).
                collect(Collectors.toList());
    }
}
