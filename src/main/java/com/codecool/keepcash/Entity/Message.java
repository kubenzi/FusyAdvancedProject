package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

}
