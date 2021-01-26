package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StatisticsService {
    List<DataSeriesDto> getDataSeriesForPeriodLineChart(Long userId, Integer period, Double balance);
    List<SeriesDto> getSeriesForPeriodPieChart(Long userId, Integer period, Double balance);
}
