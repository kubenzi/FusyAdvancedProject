package com.codecool.keepcash.Service.Currency;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;

import java.util.List;

public interface CurrencyService {
    Currency getCurrencyById(Long id);
    List<CurrencyDto> getAllCurrencies();
}
