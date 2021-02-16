package com.codecool.keepcash.Dto.Currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyDto {

    private Long id;
    private String name;
    private String signature;

    public CurrencyDto() {
    }

    @JsonCreator
    public CurrencyDto(@JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("signature") String signature) {
        this.id = id;
        this.name = name;
        this.signature = signature;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
