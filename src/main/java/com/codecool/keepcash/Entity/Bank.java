package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity(name="banks")
public class Bank {

    @Id
    @SequenceGenerator(name= "banks_id_generator", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banks_id_generator")
    private Long id;

    private String name;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public Bank(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
