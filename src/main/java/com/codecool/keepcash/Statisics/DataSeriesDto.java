package com.codecool.keepcash.Statisics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataSeriesDto {

    private String name;
    private List<SeriesDto> seriesDtos;

    @JsonCreator
    public DataSeriesDto(@JsonProperty("name") String name,
                         @JsonProperty("series") List<SeriesDto> seriesDtos) {
        this.name = name;
        this.seriesDtos = seriesDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeriesDto> getSeriesDtos() {
        return seriesDtos;
    }

    public void setSeriesDtos(List<SeriesDto> seriesDtos) {
        this.seriesDtos = seriesDtos;
    }
}
