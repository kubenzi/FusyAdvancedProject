package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.util.converters.currency.CurrencyToCurrencyDtoConverter;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountToAccountDtoConverter {

    public AccountToAccountDtoConverter() {
    }

    public static AccountDto convertToDto(Account account) {
        return new AccountDto(account.getId(),
                account.getName(),
                account.getBalance(),
                account.getAccountNumber(),
                AccountTypeToAccountTypeDtoConverter.convertToDto(account.getAccountType()),
                CurrencyToCurrencyDtoConverter.convertToDto(account.getCurrency()),
                account.isBuiltin(),
                OperationToOperationDtoConverter.convertListToDto(account.getOperations()));
    }

    public static List<AccountDto> convertListToDto(List<Account> accountsList) {
        return accountsList.stream().
                map(account -> convertToDto(account)).
                collect(Collectors.toList());
    }
}
