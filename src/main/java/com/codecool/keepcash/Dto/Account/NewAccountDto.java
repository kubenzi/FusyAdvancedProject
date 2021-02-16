package com.codecool.keepcash.Dto.Account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewAccountDto {

        private String name;
        private Double balance;
        private String accountNumber;
        private Long accountTypeId;
        private Long currencyId;
        private boolean builtin;

        public NewAccountDto() {
        }

        @JsonCreator
        public NewAccountDto(@JsonProperty("name") String name,
                          @JsonProperty("balance") Double balance,
                          @JsonProperty("accountNumber") String accountNumber,
                          @JsonProperty("accountTypeId") Long accountTypeId,
                          @JsonProperty("currencyId") Long currencyId) {
            this.name = name;
            this.balance = balance;
            this.accountNumber = accountNumber;
            this.accountTypeId = accountTypeId;
            this.currencyId = currencyId;
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

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
