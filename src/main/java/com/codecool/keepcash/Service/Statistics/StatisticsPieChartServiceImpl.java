package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Service.Operation.OperationService;

public class StatisticsPieChartServiceImpl implements StatisticsPieChartService {

    private OperationService operationService;

    public StatisticsPieChartServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }



}
