package com.codecool.keepcash.Dto.Operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OperationDto {

    private Long id;
    private String description;
    private Double value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    public OperationDto() {
    }

    @JsonCreator
    public OperationDto(@JsonProperty("id") Long id,
                        @JsonProperty("description") String description,
                        @JsonProperty("value") Double value,
                        @JsonProperty("date") Date date) {
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
