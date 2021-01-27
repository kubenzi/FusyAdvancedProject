package com.codecool.keepcash.Service.Statistics;

import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Statisics.DataSeriesDto;
import com.codecool.keepcash.Statisics.SeriesDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {

    @Mock
    private OperationService operationService;

    private StatisticsService statisticsService;

    private static final long USER_ID = 1L;
    private static final int PERIOD = 1;

    @Before
    public void setUp() throws Exception {
        statisticsService = new StatisticsServiceImpl(operationService);
        when(operationService.findAllByUserIdAndPeriod(USER_ID, PERIOD)).thenReturn(getOperationDtos());
    }

    @Test
    public void should_return_series_for_given_PERIOD_and_user() {
        // given:
        double balance = 15_000;

        SeriesDto expectedSpendingSeries = new SeriesDto("Spending", -18_000d);
        SeriesDto expectedBalanceSeries = new SeriesDto("Balance", balance);

        // when:
        List<SeriesDto> result = statisticsService.getSeriesForPieChartByUserIdAndPeriod(USER_ID, PERIOD, balance);

        // then:
        assertEquals(2, result.size());
        assertEquals(expectedSpendingSeries, result.get(0));
        assertEquals(expectedBalanceSeries, result.get(1));
    }

    @Test
    public void should_return_null_value_spending_series_when_no_operations() {
        // given:
        SeriesDto expectedSpendingSeries = new SeriesDto("Spending", 0.0d);
        when(operationService.findAllByUserIdAndPeriod(USER_ID, PERIOD)).thenReturn(emptyList());

        // when:
        List<SeriesDto> result = statisticsService.getSeriesForPieChartByUserIdAndPeriod(USER_ID, PERIOD, 0.0);

        // then:
        assertEquals(2, result.size());
        assertEquals(expectedSpendingSeries, result.get(0));
    }


    @Test
    public void should_return_data_series() throws Exception {
        double balance = 20_000d;
        String spendingName = "spending";
        String balanceName = "balance";
        List<SeriesDto> expectedSpendingSeries = singletonList(new SeriesDto(spendingName, -18_000d));
        List<SeriesDto> expectedBalanceSeries = singletonList(new SeriesDto(balanceName, 38_000d));

        when(operationService.findAllByUserIdAndPeriod(USER_ID, PERIOD)).thenReturn(getOperationDtos());

        // when:
        List<DataSeriesDto> result = statisticsService.getAllDataSeriesDtoForPeriodByUserId(USER_ID, PERIOD, balance);

        // then:
        DataSeriesDto balanceDataSeriesDto = result.get(0);
        DataSeriesDto spendingDataSeriesDto = result.get(1);

        assertEquals(2, result.size());
        assertEquals(balanceName, balanceDataSeriesDto.getName());
        assertEquals(expectedBalanceSeries, balanceDataSeriesDto.getSeriesDtos());

        assertEquals(spendingName, spendingDataSeriesDto.getName());
        assertEquals(expectedSpendingSeries, spendingDataSeriesDto.getSeriesDtos());
    }

    private List<OperationDto> getOperationDtos() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2021-01-27");

        return asList(
                new OperationDto(1L, "description 1", 2000d, date),
                new OperationDto(2L, "description 2", 1000d, date),
                new OperationDto(3L, "description 3", 5000d, date),
                new OperationDto(4L, "description 4", 10000d, date)
        );
    }


}