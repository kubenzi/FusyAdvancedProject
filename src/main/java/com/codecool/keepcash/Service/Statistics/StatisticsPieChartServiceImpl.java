package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StatisticsPieChartServiceImpl implements StatisticsPieChartService {

    private OperationService operationService;

    public StatisticsPieChartServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public List<SeriesDto> getSeriesForPieChartByCategoryIdAndPeriod(Long userId, Long categoryId, Integer period) {
        /* Return data for pie chart- two series, summary spending for category and for all of category  */
        return getSeriesForPeriodPieChart(
                operationService.findAllByCategoryIdAndPeriod(categoryId, period),
                operationService.getAllOperationsByUserId(userId)
        );
    }

    @Override
    public List<SeriesDto> getSeriesForPieChartByUserIdAndPeriod(Long userId, Integer period) {
        /* Return data for pie chart- two series, summary spending for all transaction and revenue */
        return getSeriesForPeriodPieChart(
                operationService.findAllByUserIdAndPeriod(userId, period)
        );
    }
    @Override
    public List<SeriesDto> getSeriesForPieChartByAccountIdAndPeriod(Long userId, Long categoryId, Integer period){
        return getSeriesForPeriodPieChart(
                operationService.findAllByAccountIdAndPeriod(categoryId, period),
                operationService.getAllOperationsByUserId(userId)
        );
    }

    private List<SeriesDto> getSeriesForPeriodPieChart(List<OperationDto> operationDtos) {

        Double spending = filterOperationIfSpending(operationDtos);

        Double revenue = filterOperationIfRevenue(operationDtos);

        return Arrays.asList(new SeriesDto("Revenue", revenue),
                new SeriesDto("Spending", spending));
    }


    private List<SeriesDto> getSeriesForPeriodPieChart(List<OperationDto> operationDtosCategory,
                                                       List<OperationDto> operationDtosCategoryTotal) {

        Double spending = filterOperationIfSpending(operationDtosCategory);

        Double revenue = filterOperationIfSpending(operationDtosCategoryTotal);

        return Arrays.asList(new SeriesDto("Total Spending", revenue),
                new SeriesDto("Spending", spending));
    }

    private Double filterOperationIfSpending(List<OperationDto> operationDtos) {
        return operationDtos.stream()
                .filter(operationDto -> operationDto.getValue() < 0)
                .map(OperationDto::getValue)
                .reduce(0.0, (a, b) -> a - b);
    }

    private Double filterOperationIfRevenue(List<OperationDto> operationDtos) {
        return operationDtos.stream()
                .filter(operationDto -> operationDto.getValue() > 0)
                .map(OperationDto::getValue)
                .reduce(0.0, (a, b) -> a + b);

    }


}
