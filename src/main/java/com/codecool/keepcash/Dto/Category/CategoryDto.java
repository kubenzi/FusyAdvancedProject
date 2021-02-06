package com.codecool.keepcash.Dto.Category;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoryDto {

    private Long id;
    private String name;
    private boolean builtin;
    private List<OperationDto> operations;

    public CategoryDto() {
    }

    @JsonCreator
    public CategoryDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("builtin") boolean builtin,
                       @JsonProperty("operations") List<OperationDto> operations) {
        this.id = id;
        this.name = name;
        this.builtin = builtin;
        this.operations = operations;
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

    public boolean isBuiltin() {
        return builtin;
    }

    public void setBuiltin(boolean builtin) {
        this.builtin = builtin;
    }

    public List<OperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDto> operations) {
        this.operations = operations;
    }
}
