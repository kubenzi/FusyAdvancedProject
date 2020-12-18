package com.codecool.keepcash.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class AccountTypeDto {

    private Long id;
    @JsonSetter
    private String name;

    public AccountTypeDto() {
    }

    @JsonCreator
    public AccountTypeDto(@JsonProperty ("id") Long id,
                          @JsonProperty ("name") String name) {
        this.id = id;
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
