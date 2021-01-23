package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Statistics.StatisticsService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping()
    public DataSeriesDto getAllOperationsSeriesForPeriod(Long userId, Integer period, Double balance){
        Long userTest = 2L;
        Integer periodTest = 90;
        Double balanceTest = 1000.0;
        return statisticsService.getAllOperationsSeriesForPeriod(userTest, periodTest, balanceTest);
    }
}
