package com.codecool.keepcash.Service.Currency;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
    void addNewCurrency(CurrencyDto currencyDto);
}
