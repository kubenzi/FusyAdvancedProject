package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class OperationType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public OperationType() {
    }

    public OperationType(String name) {
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
