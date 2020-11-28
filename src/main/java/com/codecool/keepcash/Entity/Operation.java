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


}
