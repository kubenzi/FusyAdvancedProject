package com.codecool.keepcash.ExternalApis.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonSetter("EUR")
    public double eur;

    @JsonSetter("USD")
    public double usd;

    @JsonSetter("GBP")
    public double gbp;

    public Rates() {
    }

    public double getEur() {
        return eur;
    }

    public double getUsd() {
        return usd;
    }

    public double getGbp() {
        return gbp;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "eur=" + eur +
                ", usd=" + usd +
                ", gbp=" + gbp +
                '}';
    }
}
