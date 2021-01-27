package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private OperationService operationService;

    public StatisticsServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    public List<SeriesDto> getSeriesForPieChartByUserIdAndPeriod(Long userId, Integer period, Double balance) {
        return Arrays.asList(getSpendingSeries(operationService.findAllByUserIdAndPeriod(userId, period)),
                new SeriesDto("Balance", balance));
    }

    public List<DataSeriesDto> getAllDataSeriesDtoForPeriodByUserId(Long userId, Integer period, Double balance) {
        return getDataSeriesForPeriodLineChart(
                operationService.findAllByUserIdAndPeriod(userId, period), period, balance);
    }

    private List<DataSeriesDto> getDataSeriesForPeriodLineChart(List<OperationDto> operationDtos,
                                                                Integer period, Double balance) {
        List<SeriesDto> seriesDtosBalance = getAllSeriesDtoForPeriod(operationDtos, period, balance, false);
        List<SeriesDto> seriesDtosSpending = getAllSeriesDtoForPeriod(operationDtos, period, 0.0, true);
        return Arrays.asList(new DataSeriesDto("balance", seriesDtosBalance),
                new DataSeriesDto("spending", seriesDtosSpending));
    }

    private SeriesDto getSpendingSeries(List<OperationDto> operationDtos) {
        Double spending = operationDtos.stream()
                .map(OperationDto::getValue)
                .reduce(0.0, (a, b) -> a - b);

        return new SeriesDto("Spending", spending);
    }

    private List<SeriesDto> getAllSeriesDtoForPeriod(List<OperationDto> operationDtos, Integer period,
                                                     Double balance, Boolean spending) {
        Map<String, Double> dayMapForPeriod = createDayMapForPeriod(operationDtos);
        Map<String, Double> filledMapWithEmptyDay = fillMapWithEmptyDay(dayMapForPeriod, period);
        Map<String, Double> sortedMapWithBalance = sortMapWithBalance(filledMapWithEmptyDay, balance, spending);
        return convertMapToDataSeriesDto(sortedMapWithBalance);
    }

    private Map<String, Double> createDayMapForPeriod(List<OperationDto> operationDtos) {
        return operationDtos.stream()
                .collect(Collectors.groupingBy(operationDto -> operationDto.getDate().toString()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(OperationDto::getValue).sum()
                ));
    }

    private Map<String, Double> fillMapWithEmptyDay(Map<String, Double> dayMapForPeriod, Integer period) {
        int counter = 0;
        LocalDate localDate = LocalDate.now().minusDays(counter);
        while (period > 0) {
            if (!dayMapForPeriod.containsKey(localDate.toString())) {
                dayMapForPeriod.put(localDate.toString(), 0.0);
            }
            period--;
            counter++;
        }
        return dayMapForPeriod;
    }

    private Map<String, Double> sortMapWithBalance(Map<String, Double> dayMapForPeriod, Double balance,
                                                   Boolean spendings) {

        TreeMap<String, Double> sortedMap = new TreeMap<>(dayMapForPeriod);

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
                .sorted(Comparator.comparing(SeriesDto::getName))
                .collect(Collectors.toList());
    }


}
