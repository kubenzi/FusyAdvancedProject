package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.*;
import com.codecool.keepcash.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    private List<User> createUsersSamples() {


        AccountType accountType = new AccountType("Savings");
        Currency currency = new Currency("złoty", "PLN");

        OperationType outcome = new OperationType("outcome");
        Category home = new Category("Home");
        List<Category> categories = Arrays.asList(home);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("01/12/2020");

        Operation operation = new Operation("New table",
                250.0, simpleDateFormat, outcome, home);

        List<Operation> operations = Arrays.asList(operation);

        Account account = new Account("PKO", 200000.0,
                "1111222211112222", accountType, currency, operations);

        List<Account> accounts = Arrays.asList(account);

        return Arrays.asList(
                new User("Przemek", "R", "pr@gmail.com",
                        "123", "prBoss", categories, accounts),
                new User("Dominik", "T", "dt@gmail.com",
                        "123", "dtGamer", categories, accounts)

        );
    }
}