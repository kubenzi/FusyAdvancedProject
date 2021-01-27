package com.codecool.keepcash.Statisics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SeriesDto {

    private String name;
    private Double value;

    @JsonCreator
    public SeriesDto(@JsonProperty("name") String name,
                     @JsonProperty("value") Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriesDto seriesDto = (SeriesDto) o;
        return Objects.equals(name, seriesDto.name) &&
                Objects.equals(value, seriesDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "SeriesDto{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
