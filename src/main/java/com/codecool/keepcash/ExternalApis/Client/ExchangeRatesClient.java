package com.codecool.keepcash.ExternalApis.Client;

import com.codecool.keepcash.ExternalApis.Dto.ExchangeRatesDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ExchangeRatesClient {
    private static final String EXCHANGE_RATES_API_URL = "https://api.exchangeratesapi.io/latest";

    private HttpClient httpClient;

    public ExchangeRatesClient() {
        this.httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    }

    public ExchangeRatesDto getCurrencies() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(EXCHANGE_RATES_API_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return deserialize(response.body());
    }

    public ExchangeRatesDto deserialize(String body) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(body, ExchangeRatesDto.class);
    }
}
