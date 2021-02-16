package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @SequenceGenerator(name= "currency_id_generator", initialValue = 3, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_id_generator")
    private Long id;

    private String name;

    private String signature;

    public Currency() {
    }

    public Currency(String name,
                    String signature) {
        this.name = name;
        this.signature = signature;
    }

    public Currency(Long id, String name, String signature) {
        this.id = id;
        this.name = name;
        this.signature = signature;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
