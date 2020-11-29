package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity(name="categories")
public class Category {

    @Id
    @SequenceGenerator(name= "id_gen", initialValue = 10, allocationSize = 1)
    @GeneratedValue
    private Long id;

    private String name;

    public Category() {
    }

    public Category(String name) {
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
