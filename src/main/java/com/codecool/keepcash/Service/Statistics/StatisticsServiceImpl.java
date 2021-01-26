package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private OperationService operationService;

    public StatisticsServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }


    public List<DataSeriesDto> getDataSeriesForPeriodLineChart(Long userId, Integer period, Double balance) {
        List<SeriesDto> seriesDtosBalance = getAllSeriesDtoForPeriod(userId, period, balance, false);
        List<SeriesDto> seriesDtosSpending = getAllSeriesDtoForPeriod(userId, period, 0.0, true);
        return Arrays.asList(new DataSeriesDto("balance", seriesDtosBalance),
                new DataSeriesDto("spending", seriesDtosSpending));
    }

    public List<SeriesDto> getSeriesForPeriodPieChart(Long userId, Integer period, Double balance) {
        List<OperationDto> allByUserIdAndPeriod = operationService.findAllByUserIdAndPeriod(userId, period);
        Double spending = allByUserIdAndPeriod.stream()
                .map(OperationDto::getValue)
                .reduce(0.0, (a, b) -> a - b);

        return Arrays.asList(new SeriesDto("Balance", balance),
                new SeriesDto("Spending", spending));
    }

    private List<SeriesDto> getAllSeriesDtoForPeriod(Long userId, Integer period, Double balance, Boolean spending) {
        Map<String, List<OperationDto>> dayMapForPeriod = createDayMapForPeriod(userId, period);
        Map<String, Double> transformDayMapForPeriod = transformDayMapForPeriod(dayMapForPeriod);
        Map<String, Double> filledMapWithEmptyDay = fillMapWithEmptyDay(transformDayMapForPeriod, period);
        Map<String, Double> sortedMapWithBalance = sortMapWithBalance(filledMapWithEmptyDay, balance, spending);
        return convertMapToDataSeriesDto(sortedMapWithBalance);
    }

    private Map<String, List<OperationDto>> createDayMapForPeriod(Long userId, Integer period) {
        return operationService.findAllByUserIdAndPeriod(userId, period)
                .stream().collect(Collectors
                        .groupingBy(operationDto -> operationDto.getDate().toString())
                );
    }

    private Map<String, Double> transformDayMapForPeriod(Map<String, List<OperationDto>> dayMapForPeriod) {
        return dayMapForPeriod.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(), entry -> entry.getValue().
                                stream().collect(Collectors.summingDouble(value -> value.getValue()))
                ));
    }

    private Map<String, Double> fillMapWithEmptyDay(Map<String, Double> dayMapForPeriod, Integer period) {
        LocalDate date = LocalDate.now();
        int counter = 0;
        while (period > 0) {
            if (!dayMapForPeriod.containsKey(LocalDate.now().minusDays(counter).toString())) {
                dayMapForPeriod.put(LocalDate.now().minusDays(counter).toString(), 0.0);
            }
            period--;
            counter++;
        }
        return dayMapForPeriod;
    }

    private Map<String, Double> sortMapWithBalance(Map<String, Double> dayMapForPeriod, Double balance,
                                                   Boolean spendings) {

        TreeMap<String, Double> sortedMap = new TreeMap<>();
        sortedMap.putAll(dayMapForPeriod);

//        return sortedMap.entrySet().stream()
//                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> balance + entry.getValue()));

        for (String key : sortedMap.keySet()) {
            Double value = sortedMap.get(key);
            if (spendings) {
                balance -= value;
            } else
                balance += value;

            sortedMap.put(key, balance);
        }
        return sortedMap;

    }

    private List<SeriesDto> convertMapToDataSeriesDto(Map<String, Double> sortMapWithBalance) {

        List<SeriesDto> seriesDtoList = sortMapWithBalance.entrySet()
                .stream()
                .map(entry -> new SeriesDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return seriesDtoList.stream()
                .sorted((series1, series2) -> series1.getName().compareTo(series2.getName()))
                .collect(Collectors.toList());
    }


}
