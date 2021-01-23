package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StatisticsService {
    DataSeriesDto getAllOperationsSeriesForPeriod(Long userId, Integer period, Double balance);
}
