package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.AccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.util.converters.currency.CurrencyToCurrencyDtoConverter;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountToAccountDtoConverter {

    private AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter;
    private CurrencyToCurrencyDtoConverter currencyToCurrencyDtoConverter;
    private OperationToOperationDtoConverter operationToOperationDtoConverter;

    public AccountToAccountDtoConverter(AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter,
                                        CurrencyToCurrencyDtoConverter currencyToCurrencyDtoConverter,
                                        OperationToOperationDtoConverter operationToOperationDtoConverter) {
        this.accountTypeToAccountTypeDtoConverter = accountTypeToAccountTypeDtoConverter;
        this.currencyToCurrencyDtoConverter = currencyToCurrencyDtoConverter;
        this.operationToOperationDtoConverter = operationToOperationDtoConverter;
    }

    public AccountDto convertToDto(Account account) {
        return new AccountDto(account.getId(),
                account.getName(),
                account.getBalance(),
                account.getAccountNumber(),
                accountTypeToAccountTypeDtoConverter.convertToDto(account.getAccountType()),
                currencyToCurrencyDtoConverter.convertToDto(account.getCurrency()),
                operationToOperationDtoConverter.convertListToDto(account.getOperations()));
    }

    public List<AccountDto> convertListToDto(List<Account> accountsList) {
        return accountsList.stream().
                map(this::convertToDto).
                collect(Collectors.toList());
    }
}
