package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.Currency;
import com.codecool.keepcash.Repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> getAllCurrency() {
        return (List<Currency>) currencyRepository.findAll();
    }

    @Override
    public Currency getCurrencyBySignature(String signature) {
        return currencyRepository.getCurrencyBySignature(signature);
    }

}
