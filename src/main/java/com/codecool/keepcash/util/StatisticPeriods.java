package com.codecool.keepcash.util;

public enum StatisticPeriods {
    _30days(30),
    _60days(60),
    _90days(90);


    private int periodDays;

    StatisticPeriods(int periodDays) {
        this.periodDays = periodDays;
    }

    public int getPeriodDays() {
        return periodDays;
    }
}
