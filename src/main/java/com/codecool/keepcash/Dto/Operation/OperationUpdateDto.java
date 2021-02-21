package com.codecool.keepcash.Dto.Operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationUpdateDto {

    private Long categoryId;

    public OperationUpdateDto() {
    }

    @JsonCreator
    public OperationUpdateDto(@JsonProperty("categoryId") Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
