package com.codecool.keepcash.Dto.Operation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;
import java.util.Date;

public class NewOperationDto {

    private String description;
    private Double value;
    private Date date;
    private Long accountId;
    private Long categoryId;


    public NewOperationDto() {
    }

    @JsonCreator
    public NewOperationDto(@JsonProperty("description") String description,
                           @JsonProperty("value") Double value,
                           @JsonProperty("accountId") Long accountId,
                           @JsonProperty("categoryId") Long categoryId,
                           @JsonProperty("date") Date date
    ) {
        this.description = description;
        this.value = value;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.date = date;
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
