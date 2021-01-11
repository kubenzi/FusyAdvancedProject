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

    AccountTypeRepository accountTypeRepository;
    AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter;
    CurrencyRepository currencyRepository;
    CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter;
    OperationDtoToOperationConverter operationDtoToOperationConverter;

    public AccountDtoToAccountConverter(AccountTypeRepository accountTypeRepository,
                                        AccountTypeDtoToAccountTypeConverter accountTypeDtoToAccountTypeConverter,
                                        CurrencyRepository currencyRepository,
                                        CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter,
                                        OperationDtoToOperationConverter operationDtoToOperationConverter) {
        this.accountTypeRepository = accountTypeRepository;
        this.accountTypeDtoToAccountTypeConverter = accountTypeDtoToAccountTypeConverter;
        this.currencyRepository = currencyRepository;
        this.currencyDtoToCurrencyConverter = currencyDtoToCurrencyConverter;
        this.operationDtoToOperationConverter = operationDtoToOperationConverter;
    }

    public Account convertDtoToAccount(AccountDto accountDto) {
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

    public Account convertNewAccountToAccount(NewAccountDto newAccountDto) {
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
