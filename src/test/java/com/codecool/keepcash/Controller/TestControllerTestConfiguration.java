package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Service.Account.AccountTypeService;
import com.codecool.keepcash.Service.Currency.CurrencyService;
import com.codecool.keepcash.Service.Operation.OperationService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class TestControllerTestConfiguration {

    @Bean
    public TestController testController() {
        return new TestController(operationService(), currencyService(), accountTypeService());
    }

    @Bean
    public OperationService operationService() {
        return Mockito.mock(OperationService.class);
    }

    @Bean
    public CurrencyService currencyService() {
        return Mockito.mock(CurrencyService.class);
    }

    @Bean
    public AccountTypeService accountTypeService() {
        return Mockito.mock(AccountTypeService.class);
    }
}
