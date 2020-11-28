package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String signature;


}
