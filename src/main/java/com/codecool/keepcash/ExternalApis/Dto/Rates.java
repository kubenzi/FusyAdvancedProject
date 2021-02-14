package com.codecool.keepcash.ExternalApis.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Double> createMapOfRates() {
        Map<String, Double> mapOfRates = new HashMap<>();
        mapOfRates.put("PLN", 1.0);
        mapOfRates.put("USD", this.getUsd());
        mapOfRates.put("EUR", this.getEur());
        mapOfRates.put("GBP", this.getGbp());

        return mapOfRates;
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
