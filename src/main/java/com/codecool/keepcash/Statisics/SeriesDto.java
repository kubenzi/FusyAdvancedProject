package com.codecool.keepcash.Statisics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SeriesDto {

    private String name;
    private Double value;

    @JsonCreator
    public SeriesDto(@JsonProperty("name") String name,
                     @JsonProperty("value") Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
