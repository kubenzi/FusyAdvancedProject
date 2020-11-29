package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrency();

    Currency getCurrencyBySignature(String signature);
}
