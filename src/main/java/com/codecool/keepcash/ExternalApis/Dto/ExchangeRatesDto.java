package com.codecool.keepcash.ExternalApis.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesDto {

    @JsonSetter("PLN")
    private String ratingPLN;

    public ExchangeRatesDto() {
    }

    public String getRatingPLN() {
        return ratingPLN;
    }

    @Override
    public String toString() {
        return "ExchangeRatesDto{" +
                "ratingPLN='" + ratingPLN + '\'' +
                '}';
    }
}
