package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Service.Operation.OperationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private OperationService operationService;

    public StatisticsServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public Map<String, List<OperationDto>> CreateDayMapForPeriod(Long userId, Integer period){
        return operationService.findAllByUserIdAndPeriod(userId, period)
                .stream().collect(Collectors
                        .groupingBy(operationDto -> operationDto.getDate().toString())
                );
    }
}
