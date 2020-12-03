package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.CurrencyDto;
import com.codecool.keepcash.Dto.OperationDto;
import com.codecool.keepcash.Service.CurrencyService;
import com.codecool.keepcash.Service.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    public OperationService operationService;
    public CurrencyService currencyService;

    public TestController(OperationService operationService,
                          CurrencyService currencyService) {
        this.operationService = operationService;
        this.currencyService = currencyService;
    }

    @GetMapping("/transactions")
    public List<OperationDto> getOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/currencies")
    public List<CurrencyDto> getCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @PostMapping("/currencies")
    @ResponseStatus(CREATED)
    public void addCurrency(@RequestBody CurrencyDto currencyDto) {
        currencyService.addNewCurrency(currencyDto);
    }
}
