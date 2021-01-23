package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    public DataSeriesDto getAllOperationsSeriesForPeriod(Long userId, Integer period, Double balance) {

        Map<String, List<OperationDto>> dayMapForPeriod = createDayMapForPeriod(userId, period);
        Map<String, Double> transformDayMapForPeriod = transformDayMapForPeriod(dayMapForPeriod);
        Map<String, Double> filledMapWithEmptyDay = fillMapWithEmptyDay(transformDayMapForPeriod, period);
        Map<String, Double> sortedMapWithBalance = sortMapWithBalance(filledMapWithEmptyDay, balance);
        List<SeriesDto> seriesDtos = convertMapToDataSeriesDto(sortedMapWithBalance);
        return new DataSeriesDto("balance", seriesDtos);

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

    private Map<String, Double> sortMapWithBalance(Map<String, Double> dayMapForPeriod, Double balance) {

        TreeMap<String, Double> sortedMap = new TreeMap<>();
        sortedMap.putAll(dayMapForPeriod);

//        return sortedMap.entrySet().stream()
//                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> balance + entry.getValue()));

        for (String key : sortedMap.keySet()) {
            Double value = sortedMap.get(key);
            balance += value;
            sortedMap.put(key, balance);
        }
        return sortedMap;

    }

    private List<SeriesDto> convertMapToDataSeriesDto(Map<String, Double> sortMapWithBalance){

        List<SeriesDto> seriesDtoList = sortMapWithBalance.entrySet()
                .stream()
                .map(entry -> new SeriesDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return seriesDtoList.stream()
                .sorted((series1, series2) -> series2.getName().compareTo(series1.getName()))
                .collect(Collectors.toList());
    }


}
