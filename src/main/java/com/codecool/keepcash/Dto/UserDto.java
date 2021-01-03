package com.codecool.keepcash.Dto;

import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class UserDto {

    private Long id;

    @JsonSetter
    private String firstName;

    @JsonSetter
    private String lastName;

    @JsonSetter
    private String email;

    @JsonSetter
    private String username;

    @JsonSetter
    private List<Category> categories;

    @JsonSetter
    private List<Account> accounts;

    public UserDto(String firstName,
                   String lastName,
                   String email,
                   String username,
                   List<Category> categories,
                   List<Account> accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.categories = categories;
        this.accounts = accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
