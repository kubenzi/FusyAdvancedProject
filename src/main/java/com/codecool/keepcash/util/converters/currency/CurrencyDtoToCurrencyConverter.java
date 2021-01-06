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

    public Currency convertDtoToCurrency(CurrencyDto currencyDto) {
        return new Currency(currencyDto.getName(), currencyDto.getSignature());
    }

    public List<Currency> convertDtoToList(List<CurrencyDto> currencies) {
        return currencies.stream()
                .map(this::convertDtoToCurrency)
                .collect(Collectors.toList());
    }
}
