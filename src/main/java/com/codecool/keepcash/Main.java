package com.codecool.keepcash;

import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Service.CurrencyService;
import com.codecool.keepcash.Service.OperationService;
import com.codecool.keepcash.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

	private UserService userService;
	private CurrencyService currencyService;
	private OperationService operationService;

	public Main(UserService userService, CurrencyService currencyService, OperationService operationService) {
		this.userService = userService;
		this.currencyService = currencyService;
		this.operationService = operationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

	}

	@Bean
	public CommandLineRunner runner() {
		return (args) -> {
			System.out.println("User ID: 1: " + userService.getUserById(1L));
			System.out.println("User ID: 10 (doesn't exist)" + userService.getUserById(10L));
			List<Category> krzysieksCategories = new ArrayList<>();
			List<Account> krzysieksAccounts = new ArrayList<>();
 			User krzysiek = new User("Krzysztof", "Chromiec", "krzysztof.chromiec1@gmail.com", "Niemce15", "naczelnyFus", krzysieksCategories, krzysieksAccounts);
			System.out.println(userService.addUser(krzysiek));
			System.out.println("All currency: " + currencyService.getAllCurrency());
			System.out.println("Currency by signature: EUR " + currencyService.getCurrencyBySignature("EUR"));
			System.out.println("Operation ID: 2 " + operationService.getOperationById(2L));



		};
	}

}
