package com.codecool.keepcash.Entity;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name="accounts")
public class Account {

    @Id
    @SequenceGenerator(name= "account_id_generator", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_generator")
    private Long id;

    private String name;

    private Double balance;

    private String accountNumber;

    @OneToOne
    private AccountType accountType;

    @OneToOne
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    List<Operation> operations = new ArrayList<>();

    public Account() {
    }

    public Account(String name,
                   Double balance,
                   String accountNumber,
                   AccountType accountType,
                   Currency currency,
                   List<Operation> operations) {
        this.name = name;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currency = currency;
        this.operations = operations;
    }

    public Account(Long id,
                   String name,
                   Double balance,
                   String accountNumber,
                   AccountType accountType,
                   Currency currency,
                   List<Operation> operations) {
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
