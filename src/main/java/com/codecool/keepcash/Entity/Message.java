package com.codecool.keepcash.Entity;

import javax.persistence.*;

@Entity(name="messages")
public class Message {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    public Message() {
    }

    public Message(String description) {
        this.description = description;
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
}
