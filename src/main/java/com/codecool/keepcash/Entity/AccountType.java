package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class AccountType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;



}
