package com.codecool.keepcash.util.converters.currency;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyDtoToCurrencyConverter {

    public CurrencyDtoToCurrencyConverter() {
    }

    public static Currency convertDtoToCurrency(CurrencyDto currencyDto) {
        return new Currency(currencyDto.getName(), currencyDto.getSignature());
    }

    public static List<Currency> convertDtoToList(List<CurrencyDto> currencies) {
        return currencies.stream()
                .map(currencyDto -> convertDtoToCurrency(currencyDto))
                .collect(Collectors.toList());
    }
}
