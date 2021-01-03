package com.codecool.keepcash.Dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationTypeDto {

    private Long id;
    private String name;

    public OperationTypeDto() {
    }

    @JsonCreator
    public OperationTypeDto(@JsonProperty("id") Long id,
                            @JsonProperty("name") String name) {
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
