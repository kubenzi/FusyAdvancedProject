package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue
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
