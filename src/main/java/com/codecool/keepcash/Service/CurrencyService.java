package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrencies();
    void addNewCurrency(CurrencyDto currencyDto);
}
