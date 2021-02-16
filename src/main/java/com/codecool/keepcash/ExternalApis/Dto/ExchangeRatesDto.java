package com.codecool.keepcash.ExternalApis.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesDto {

    @JsonSetter("rates")
    private Rates rates;

    @JsonSetter("base")
    private String base;

    @JsonSetter("date")
    private Date date;

    public ExchangeRatesDto() {
    }

    public Rates getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    private String simpleDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "ExchangeRatesDto{" +
                "rates=" + rates.toString() +
                ", base='" + base + '\'' +
                ", date='" + simpleDate(date) + '\'' +
                '}';
    }


}
