package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Service.AccountTypeService;
import com.codecool.keepcash.util.converters.account.AccountTypeToAccountTypeDtoConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestControllerTestConfiguration.class})
@WebAppConfiguration
public class TestControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    AccountTypeService accountTypeService;
    AccountTypeToAccountTypeDtoConverter accountTypeToAccountTypeDtoConverter;

    @Before
    public void set() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_return_all_account_types() throws Exception {
        when(accountTypeService.getAllAccountTypes())
                .thenReturn(buildDummyAccountTypes());

        mockMvc.perform(get("/api/v1/account-types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name").value("Euro"))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[1].name").value("Dollars"))
                .andExpect(jsonPath("$.[1].id").value(2L));

    }

    @Test
    public void should_return_created_status_code_when_adding_account_type() throws Exception {
        mockMvc.perform(post("/api/v1/account-types").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertAccountTypeToJson(new AccountTypeDto(3L, "Lir"))))
                .andExpect(status().isCreated());
    }

    private String convertAccountTypeToJson(AccountTypeDto accountTypeDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(accountTypeDto);
    }


    private List<AccountTypeDto> buildDummyAccountTypes() {
        return asList(
                new AccountTypeDto(1L, "Euro"),
                new AccountTypeDto(2L, "Dollars")
        );
    }


}


