package com.codecool.keepcash.util.converters.account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.Repository.CurrencyRepository;
import com.codecool.keepcash.util.converters.currency.CurrencyDtoToCurrencyConverter;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountDtoToAccountConverter {

    public AccountDtoToAccountConverter() {
    }

    public static Account convertDtoToAccount(AccountDto accountDto) {
        return new Account(accountDto.getName(),
                accountDto.getBalance(),
                accountDto.getAccountNumber(),
                AccountTypeDtoToAccountTypeConverter.convertDtoToAccountType(accountDto.getAccountType()),
                CurrencyDtoToCurrencyConverter.convertDtoToCurrency(accountDto.getCurrency()),
                OperationDtoToOperationConverter.convertDtoListToOperationList(accountDto.getOperations()));
    }

    public static List<Account> convertDtoToList(List<AccountDto> accounts) {
        return accounts.stream().
                map(accountDto -> convertDtoToAccount(accountDto)).
                collect(Collectors.toList());
    }

    public static Account convertNewAccountToAccount(NewAccountDto newAccountDto,
                                              AccountTypeRepository accountTypeRepository,
                                              CurrencyRepository currencyRepository
                                              ) {
        try {
            return new Account(newAccountDto.getName(),
                    newAccountDto.getBalance(),
                    newAccountDto.getAccountNumber(),
                    accountTypeRepository.findById(newAccountDto.getAccountTypeId()).get(),
                    currencyRepository.findById(newAccountDto.getCurrencyId()).get());
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException("AccountType ID or Currency ID not found");
        }
    }
}
