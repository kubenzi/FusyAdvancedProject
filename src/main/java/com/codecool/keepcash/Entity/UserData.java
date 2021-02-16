package com.codecool.keepcash.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="user_data")
public class UserData {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Account> accounts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public UserData() {
    }

    public UserData(String firstName,
                    String lastName,
                    String email,
                    User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user = user;
    }

    public UserData(Long id,
                    String firstName,
                    String lastName,
                    String email,
                    List<Category> categories,
                    List<Account> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.categories = categories;
        this.accounts = accounts;
    }

    public UserData(Long id,
                    String firstName,
                    String lastName,
                    String email,
                    List<Category> categories,
                    List<Account> accounts,
                    User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.categories = categories;
        this.accounts = accounts;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
