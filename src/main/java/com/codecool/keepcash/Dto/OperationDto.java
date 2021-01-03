package com.codecool.keepcash.Dto;

import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.OperationType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OperationDto {

    private Long id;
    private String description;
    private Double value;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonIgnore
    private Date date;
    @JsonIgnore
    private OperationType operationType;
    @JsonIgnore
    private Category category;
    @JsonIgnore
    private Account account;

    public OperationDto() {
    }

    public OperationDto(Long id,
                        String description,
                        Double value,
                        Date date,
                        OperationType operationType,
                        Category category,
                        Account account) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.date = date;
        this.operationType = operationType;
        this.category = category;
        this.account = account;
    }

    @JsonCreator
    public OperationDto(@JsonProperty("id") Long id,
                        @JsonProperty("description") String description,
                        @JsonProperty("value") Double value) {
        this.id = id;
        this.description = description;
        this.value = value;
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

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
