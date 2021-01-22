package com.codecool.keepcash.util;

public enum StatisticPeriods {
    _30days(30),
    _60days(60),
    _180days(180),
    _360days(360);

    private int periodDays;

    StatisticPeriods(int periodDays) {
        this.periodDays = periodDays;
    }

    public int getPeriodDays() {
        return periodDays;
    }
}
