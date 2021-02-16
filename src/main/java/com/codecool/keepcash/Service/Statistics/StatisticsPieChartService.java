package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Statisics.SeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticsPieChartService {

    List<SeriesDto> getSeriesForPieChartByUserIdAndPeriod(Long userId, Integer period);

    List<SeriesDto> getSeriesForPieChartByCategoryIdAndPeriod(Long UserId, Long categoryId, Integer period);

    List<SeriesDto> getSeriesForPieChartByAccountIdAndPeriod(Long userId, Long categoryId, Integer period);
}
