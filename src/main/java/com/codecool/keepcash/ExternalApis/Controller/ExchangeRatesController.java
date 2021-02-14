package com.codecool.keepcash.ExternalApis.Controller;

import com.codecool.keepcash.ExternalApis.Client.ExchangeRatesClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class ExchangeRatesController {

    private ExchangeRatesClient exchangeRatesClient;

    public ExchangeRatesController(ExchangeRatesClient exchangeRatesClient) {
        this.exchangeRatesClient = exchangeRatesClient;
    }

    @GetMapping("/exchange-rates")
    public String getRates() throws InterruptedException, IOException, URISyntaxException {
        return exchangeRatesClient.getCurrencies().toString();
    }

    @GetMapping("/historical-exchange-rates")
    public String getHistoricalRates() throws InterruptedException, IOException, URISyntaxException {
        return exchangeRatesClient.getHistoricalCurrencies().toString();
    }
}
