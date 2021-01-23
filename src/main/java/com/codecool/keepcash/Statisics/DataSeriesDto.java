package com.codecool.keepcash.Statisics;

import java.util.List;

public class DataSeriesDto {

    private String name;
    private List<SeriesDto> seriesDtos;

    public DataSeriesDto(String name, List<SeriesDto> seriesDtos) {
        this.name = name;
        this.seriesDtos = seriesDtos;
    }
}
