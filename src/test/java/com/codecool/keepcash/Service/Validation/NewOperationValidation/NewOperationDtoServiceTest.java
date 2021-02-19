package com.codecool.keepcash.Service.Validation.NewOperationValidation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Service.Validation.ValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codecool.keepcash.Service.Validation.ValidationError.AT_LEAST_ONE_VALUE_IS_NULL;
import static com.codecool.keepcash.Service.Validation.ValidationError.TO_LONG_DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class NewOperationDtoServiceTest {


    @InjectMocks
    private NewOperationDtoService newOperationDtoService;


    @Test
    public void should_return_true_if_data_valid() {
        // given
        NewOperationDto newOperationDto = prepare_valid_newOperationDto();

        // when

        List<ValidationError> errors = newOperationDtoService.validateNewOperationDto(newOperationDto);

        // then
        System.out.println(errors);
        assertFalse(errors.contains(AT_LEAST_ONE_VALUE_IS_NULL));
    }

    @Test
    public void should_return_not_valid_of_null() {
        // given
        NewOperationDto newOperationDto = prepare_not_valid_newOperationDto_for_null();

        // when
        List<ValidationError> validationErrors1 = newOperationDtoService.
                validateNewOperationDto(newOperationDto);

        // then
        System.out.println(validationErrors1);
        assertTrue(validationErrors1.contains(AT_LEAST_ONE_VALUE_IS_NULL));
    }

    @Test
    public void should_return_not_valid_of_description() {
        // given
        NewOperationDto newOperationDto = prepare_not_valid_newOperationDto_for_description();

        // when
        List<ValidationError> validationErrors1 = newOperationDtoService.
                validateNewOperationDto(newOperationDto);

        // then
        System.out.println(validationErrors1);
        assertTrue(validationErrors1.contains(TO_LONG_DESCRIPTION));
    }

    private NewOperationDto prepare_valid_newOperationDto() {
        String date = "2018-05-05";

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        try {
            date1 = formatter1.parse(date);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        return new NewOperationDto(
                "test des",
                10.0,
                1L,
                2L,
                date1
        );
    }

    private NewOperationDto prepare_not_valid_newOperationDto_for_null() {
        String date = "2018-05-05";

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        try {
            date1 = formatter1.parse(date);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        return new NewOperationDto(
                null,
                null,
                1000L,
                2L,
                date1
        );
    }

    private NewOperationDto prepare_not_valid_newOperationDto_for_description() {
        String date = "2018-05-05";

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        try {
            date1 = formatter1.parse(date);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        return new NewOperationDto(
                "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
                        "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
                10.0,
                1000L,
                2L,
                date1
        );
    }
}
