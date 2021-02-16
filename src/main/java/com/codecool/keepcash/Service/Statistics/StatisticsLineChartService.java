package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsLineChartService {
    List<DataSeriesDto> getAllDataSeriesDtoForPeriodByUserId(Long userId, Integer period, Double balance);

    List<DataSeriesDto> getDataSeriesForLineChartByCategoryIdIdAndPeriod(Long categoryId, Integer period,
                                                                         Double balance, Long userId);
    List<DataSeriesDto> getDataSeriesForLineChartByAccountIdIdAndPeriod(Long categoryId, Integer period,
                                                                                       Double balance, Long userId);
}
