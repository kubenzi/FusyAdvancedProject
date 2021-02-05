package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsLineChartService {
    List<DataSeriesDto> getAllDataSeriesDtoForPeriodByUserId(Long userId, Integer period, Double balance);

    List<DataSeriesDto> getDataSeriesForLineChartByCategoryIdIdAndPeriod(Long categoryId, Integer period, Double balance);

    List<SeriesDto> getSeriesForPieChartByUserIdAndPeriod(Long userId, Integer period);

    List<SeriesDto> getSeriesForPieChartByCategoryIdAndPeriod(Long UserId, Long categoryId, Integer period);
}
