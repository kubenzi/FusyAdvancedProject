package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Service.Statistics.StatisticsService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/users/{userId}/line-chart/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForPeriod(@PathVariable Long userId,
                                                               @PathVariable Integer period, Double balance) {
        Integer periodTest = 60;
        Double balanceTest = 1000.0;

        return statisticsService.getAllDataSeriesDtoForPeriodByUserId(userId, period, balanceTest);
    }

    @GetMapping("/users/{userId}/pie-chart/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPeriodPieChart(@PathVariable Long userId,
                                                                   @PathVariable Integer period, Double balance) {
        Integer periodTest = 60;
        Double balanceTest = 1000.0;
        return statisticsService.getSeriesForPieChartByUserIdAndPeriod(userId, period, balanceTest);

    }

    @GetMapping("/users/{userId}/line-chart/categories/{categoryId}/period/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForCategory(@PathVariable Long categoryId,
                                                                 @PathVariable Integer period, Double balance) {
        Integer periodTest = 60;
        Double balanceTest = 1000.0;
        return statisticsService.getDataSeriesForLineChartByCategoryIdIdAndPeriod(categoryId, period, balanceTest);

    }
}
