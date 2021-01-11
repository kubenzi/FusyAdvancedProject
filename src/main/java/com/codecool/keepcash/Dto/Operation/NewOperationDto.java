package com.codecool.keepcash.Dto.Operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewOperationDto {


    private Long id;
    private String description;
    private Double value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private Long operationTypeId;
    private Long accountId;
    private Long categoryId;


    public NewOperationDto() {
    }

    @JsonCreator
    public NewOperationDto(@JsonProperty("id") Long id,
                           @JsonProperty("description") String description,
                           @JsonProperty("value") Double value,
                           @JsonProperty("date") Date date,
                           @JsonProperty("operationTypeId") Long operationTypeId,
                           @JsonProperty("accountId") Long accountId,
                           @JsonProperty("categoryId") Long categoryId
    ) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
        this.operationTypeId = operationTypeId;
        this.accountId = accountId;
        this.categoryId = categoryId;
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

    public Long getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Long operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
