package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Service.Statistics.StatisticsLineChartService;
import com.codecool.keepcash.Service.Statistics.StatisticsPieChartService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    private StatisticsLineChartService statisticsLineChartService;
    private StatisticsPieChartService statisticsPieChartService;

    public StatisticsController(StatisticsLineChartService statisticsLineChartService,
                                StatisticsPieChartService statisticsPieChartService) {
        this.statisticsLineChartService = statisticsLineChartService;
        this.statisticsPieChartService = statisticsPieChartService;
    }

    @GetMapping("/users/{userId}/line-chart/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForPeriod(@PathVariable Long userId,
                                                               @PathVariable Integer period, Double balance) {
        Double balanceTest = 3_000.0;

        return statisticsLineChartService.getAllDataSeriesDtoForPeriodByUserId(userId, period, balanceTest);
    }

    @GetMapping("/users/{userId}/line-chart/categories/{categoryId}/period/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForCategory(@PathVariable Long categoryId,
                                                                 @PathVariable Integer period, Double balance,
                                                                 @PathVariable Long userId) {
        Integer periodTest = 60;
        Double balanceTest = 3_000.0;
        return statisticsLineChartService.getDataSeriesForLineChartByCategoryIdIdAndPeriod(
                categoryId, period, balanceTest, userId);
    }

    @GetMapping("/users/{userId}/pie-chart/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPieChartForUserIdAndPeriod(@PathVariable Long userId,
                                                                               @PathVariable Integer period) {
        Double balanceTest = 1000.0;
        return statisticsPieChartService.getSeriesForPieChartByUserIdAndPeriod(userId, period);

    }


    @GetMapping("/users/{userId}/pie-chart/categories/{categoryId}/period/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPieChartForCategoryIdAndPeriod(@PathVariable Long userId,
                                                                                   @PathVariable Long categoryId,
                                                                                   @PathVariable Integer period) {
        return statisticsPieChartService.getSeriesForPieChartByCategoryIdAndPeriod(userId, categoryId, period);

    }

    @GetMapping("/users/{userId}/line-chart/accounts/{accountId}/period/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForAccount(@PathVariable Long accountId,
                                                                 @PathVariable Integer period, Double balance,
                                                                 @PathVariable Long userId) {
        Integer periodTest = 60;
        Double balanceTest = 3_000.0;
        return statisticsLineChartService.getDataSeriesForLineChartByAccountIdIdAndPeriod(
                accountId, period,
                balanceTest,
                userId);
    }

    @GetMapping("/users/{userId}/pie-chart/accounts/{accountId}/period/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPieChartForAccountIdAndPeriod(@PathVariable Long userId,
                                                                                   @PathVariable Long accountId,
                                                                                   @PathVariable Integer period) {
        return statisticsPieChartService.getSeriesForPieChartByAccountIdAndPeriod(userId, accountId, period);

    }

}
