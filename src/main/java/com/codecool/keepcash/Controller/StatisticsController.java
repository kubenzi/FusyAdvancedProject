package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Statistics.StatisticsLineChartService;
import com.codecool.keepcash.Service.Statistics.StatisticsPieChartService;
import com.codecool.keepcash.Service.User.UserService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StatisticsController {

    private StatisticsLineChartService statisticsLineChartService;
    private StatisticsPieChartService statisticsPieChartService;
    private AccountService accountService;
    private UserService userService;

    public StatisticsController(StatisticsLineChartService statisticsLineChartService,
                                StatisticsPieChartService statisticsPieChartService,
                                AccountService accountService,
                                UserService userService) {
        this.statisticsLineChartService = statisticsLineChartService;
        this.statisticsPieChartService = statisticsPieChartService;
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/line-chart/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForPeriod(@PathVariable Long userId, @PathVariable Integer period)
            throws InterruptedException, IOException, URISyntaxException {

        Double totalBalance = userService.calculateTotalBalanceInPLN(userId);

        return statisticsLineChartService.getAllDataSeriesDtoForPeriodByUserId(userId, period, totalBalance);
    }

    @GetMapping("/users/{userId}/line-chart/categories/{categoryId}/period/{period}")
    @ResponseStatus(OK)
    public List<DataSeriesDto> getAllOperationsSeriesForCategory(@PathVariable Long categoryId,
                                                                 @PathVariable Integer period,
                                                                 @PathVariable Long userId)
            throws InterruptedException, IOException, URISyntaxException {

        Double totalBalance = userService.calculateTotalBalanceInPLN(userId);
        return statisticsLineChartService.getDataSeriesForLineChartByCategoryIdIdAndPeriod(
                categoryId, period, totalBalance, userId);
    }

    @GetMapping("/users/{userId}/pie-chart/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPieChartForUserIdAndPeriod(@PathVariable Long userId,
                                                                               @PathVariable Integer period) {
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
                                                                @PathVariable Integer period,
                                                                @PathVariable Long userId) {

        return statisticsLineChartService.getDataSeriesForLineChartByAccountIdIdAndPeriod(
                accountId, period, userId);
    }

    @GetMapping("/users/{userId}/pie-chart/accounts/{accountId}/period/{period}")
    @ResponseStatus(OK)
    public List<SeriesDto> getAllOperationsSeriesForPieChartForAccountIdAndPeriod(@PathVariable Long userId,
                                                                                  @PathVariable Long accountId,
                                                                                  @PathVariable Integer period) {
        return statisticsPieChartService.getSeriesForPieChartByAccountIdAndPeriod(userId, accountId, period);

    }
}
