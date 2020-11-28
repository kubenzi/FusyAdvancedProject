package com.codecool.keepcash.Entity;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
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





}
