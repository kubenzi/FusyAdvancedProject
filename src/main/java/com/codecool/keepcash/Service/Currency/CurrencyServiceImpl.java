package com.codecool.keepcash.Service.Currency;

import com.codecool.keepcash.Dto.Currency.CurrencyDto;
import com.codecool.keepcash.Entity.Currency;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.CurrencyRepository;
import com.codecool.keepcash.util.converters.currency.CurrencyToCurrencyDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency getCurrencyById(Long id) {
        Optional<Currency> maybeCurrency = currencyRepository.findById(id);

        if (maybeCurrency.isPresent()) {
            return maybeCurrency.get();
        } else {
            throw new IdNotFoundException(id, Currency.class.getSimpleName());
        }
    }

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        List<Currency> allCurrencies = (List<Currency>) currencyRepository.findAll();

        return CurrencyToCurrencyDtoConverter.convertListToDto(allCurrencies);
    }
}
