package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Service.Statistics.StatisticsLineChartService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    private StatisticsLineChartService statisticsLineChartService;

    public StatisticsController(StatisticsLineChartService statisticsLineChartService) {
        this.statisticsLineChartService = statisticsLineChartService;
    }

    @GetMapping("/users/{userId}/line-chart/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForPeriod(@PathVariable Long userId,
                                                               @PathVariable Integer period, Double balance) {
        Double balanceTest = 1000.0;

        return statisticsLineChartService.getAllDataSeriesDtoForPeriodByUserId(userId, period, balanceTest);
    }

    @GetMapping("/users/{userId}/line-chart/categories/{categoryId}/period/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForCategory(@PathVariable Long categoryId,
                                                                 @PathVariable Integer period, Double balance) {
        Integer periodTest = 60;
        Double balanceTest = 1000.0;
        return statisticsLineChartService.getDataSeriesForLineChartByCategoryIdIdAndPeriod(categoryId, period, balanceTest);
    }

//    @GetMapping("/users/{userId}/pie-chart/{period}")
//    @ResponseStatus(OK)
//    public List<SeriesDto> getAllOperationsSeriesForPieChartForUserIdAndPeriod(@PathVariable Long userId,
//                                                                               @PathVariable Integer period) {
//        Double balanceTest = 1000.0;
//        return statisticsService.getSeriesForPieChartByUserIdAndPeriod(userId, period);
//
//    }
//
//
//    @GetMapping("/users/{userId}/pie-chart/categories/{categoryId}/period/{period}")
//    @ResponseStatus(OK)
//    public List<SeriesDto> getAllOperationsSeriesForPieChartForCategoryIdAndPeriod(@PathVariable Long userId,
//                                                                                   @PathVariable Long categoryId,
//                                                                                   @PathVariable Integer period) {
//        Double balanceTest = 1000.0;
//        return statisticsService.getSeriesForPieChartByCategoryIdAndPeriod(userId, categoryId, period);
//
//    }
}
