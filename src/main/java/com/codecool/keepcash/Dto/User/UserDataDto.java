package com.codecool.keepcash.Dto.User;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDataDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CategoryDto> categories;
    private List<AccountDto> accounts;

    @JsonCreator
    public UserDataDto(@JsonProperty ("id") Long id,
                   @JsonProperty ("firstName") String firstName,
                   @JsonProperty ("lastName") String lastName,
                   @JsonProperty ("email") String email,
                   @JsonProperty ("categories") List<CategoryDto> categories,
                   @JsonProperty ("accounts") List<AccountDto> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.categories = categories;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
}

