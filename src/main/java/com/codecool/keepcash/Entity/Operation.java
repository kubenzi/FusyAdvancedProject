package com.codecool.keepcash.Entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Double value;

    private Date date;

    @OneToOne
    private OperationType operationType;

    @OneToOne
    private Category category;

    public Operation() {
    }

    public Operation(String description,
                     Double value,
                     Date date,
                     OperationType operationType,
                     Category category) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.operationType = operationType;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
