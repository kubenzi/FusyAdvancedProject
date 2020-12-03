package com.codecool.keepcash.util;

import com.codecool.keepcash.Dto.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyDtoToCurrencyConverter {

    public CurrencyDtoToCurrencyConverter() {
    }

    public Currency convertDtoToCurrency(CurrencyDto currencyDto) {
        return new Currency(currencyDto.getName(), currencyDto.getSignature());
    }
}
