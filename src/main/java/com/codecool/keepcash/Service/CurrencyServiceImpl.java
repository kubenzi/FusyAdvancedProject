package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;
import com.codecool.keepcash.Repository.CurrencyRepository;
import com.codecool.keepcash.util.converters.currency.CurrencyDtoToCurrencyConverter;
import com.codecool.keepcash.util.converters.currency.CurrencyToCurrencyDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;
    private CurrencyToCurrencyDtoConverter currencyToCurrencyDtoConverter;
    private CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository,
                               CurrencyToCurrencyDtoConverter currencyToCurrencyDtoConverter,
                               CurrencyDtoToCurrencyConverter currencyDtoToCurrencyConverter) {
        this.currencyRepository = currencyRepository;
        this.currencyToCurrencyDtoConverter = currencyToCurrencyDtoConverter;
        this.currencyDtoToCurrencyConverter = currencyDtoToCurrencyConverter;

    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<Currency> allCurrencies = (List<Currency>) currencyRepository.findAll();

        return currencyToCurrencyDtoConverter.convertListToDto(allCurrencies);
    }

    @Override
    public void addNewCurrency(CurrencyDto currencyDto) {
        currencyRepository.save(currencyDtoToCurrencyConverter.convertDtoToCurrency(currencyDto));
    }
}
