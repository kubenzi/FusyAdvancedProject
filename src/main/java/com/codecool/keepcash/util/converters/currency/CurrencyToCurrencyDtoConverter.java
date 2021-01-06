package com.codecool.keepcash.util.converters.currency;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyToCurrencyDtoConverter {

    public CurrencyToCurrencyDtoConverter() {
    }

    public CurrencyDto convertToDto(Currency currency) {
        return new CurrencyDto(currency.getId(),
                currency.getName(),
                currency.getSignature());
    }

    public List<CurrencyDto> convertListToDto(List<Currency> currencies) {
        return currencies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
