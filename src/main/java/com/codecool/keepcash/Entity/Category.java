package com.codecool.keepcash.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="categories")
public class Category {

    @Id
    @SequenceGenerator(name= "category_id_generator", initialValue = 11, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_generator")
    private Long id;

    private String name;

    private boolean builtin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_id")
    private List<Operation> operations = new ArrayList<>();

    public Category() {
    }

    public Category(String name, boolean builtin) {
        this.name = name;
        this.builtin = builtin;
    }

    public Category(String name, boolean builtin, List<Operation> operations) {
        this.name = name;
        this.builtin = builtin;
        this.operations = operations;
    }

    public Category(Long id, String name, boolean builtin, List<Operation> operations) {
        this.id = id;
        this.name = name;
        this.builtin = builtin;
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

    public boolean isBuiltin() {
        return builtin;
    }

    public void setBuiltin(boolean builtin) {
        this.builtin = builtin;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}
