package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Service.User.UserService;

public class UserDataChangeValidationService {

    private final UserService userService;

    public UserDataChangeValidationService(UserService userService) {
        this.userService = userService;
    }
}
