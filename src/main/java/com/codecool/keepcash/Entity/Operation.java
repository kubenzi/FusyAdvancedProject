package com.codecool.keepcash.Entity;

import javax.persistence.*;
import java.util.Date;


@Entity(name="operations")
public class Operation {

    @Id
    @SequenceGenerator(name= "operation_id_generator", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_id_generator")
    private Long id;

    private String description;

    private Double value;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne(cascade = CascadeType.PERSIST)
    private OperationType operationType;


    public Operation() {
    }

    public Operation(String description,
                     Double value,
                     Date date,
                     OperationType operationType) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.operationType = operationType;
    }

    public Operation(Long id,
                     String description,
                     Double value,
                     Date date,
                     OperationType operationType) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
        this.operationType = operationType;
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
}
