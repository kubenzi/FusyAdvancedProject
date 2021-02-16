package com.codecool.keepcash.ExternalApis.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalExchangeRatesDto {

    @JsonSetter("rates")
    private Date date;

}
