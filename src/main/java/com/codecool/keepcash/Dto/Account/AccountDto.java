package com.codecool.keepcash.Dto.Account;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountDto {
    private Long id;
    private String name;
    private Double balance;
    private String accountNumber;
    private AccountTypeDto accountType;
    private CurrencyDto currency;
    private List<OperationDto> operations;

    public AccountDto() {
    }

    @JsonCreator
    public AccountDto(@JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("balance") Double balance,
                      @JsonProperty("accountNumber") String accountNumber,
                      @JsonProperty("accountType") AccountTypeDto accountType,
                      @JsonProperty("currency") CurrencyDto currency,
                      @JsonProperty("operations") List<OperationDto> operations) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currency = currency;
        this.operations = operations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountTypeDto getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeDto accountType) {
        this.accountType = accountType;
    }

    public CurrencyDto getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDto currency) {
        this.currency = currency;
    }

    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }
}
