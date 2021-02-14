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

    // to be changed to BigDecimal
    private Double value;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Operation() {
    }

    public Operation(String description,
                     Double value,
                     Date date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public Operation(Long id,
                     String description,
                     Double value,
                     Date date) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
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
}
