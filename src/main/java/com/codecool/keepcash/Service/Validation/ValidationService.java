package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class ValidationService {

    private UserService userService;

    public ValidationService(UserService userService) {
        this.userService = userService;
    }

    public Validator registrationDtoNullValidation(UserRegistrationDto registrationDto) {

        return Validator.of(registrationDto)
                .validate(form -> form.getFirstName() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getLastName() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getUsername() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getPassword() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getEmail() != null, AT_LEAST_ONE_VALUE_IS_NULL);
    }

    public Validator registrationDtoDataValidation(UserRegistrationDto registrationDto) {
        return Validator.of(registrationDto)
                .validate(form -> Pattern.matches("^[a-zA-Z-]{2,50}$",
                        form.getFirstName()), FIRST_NAME_NOT_VALID)
                .validate(form -> Pattern.matches("^[a-zA-Z-]{2,50}$",
                        form.getLastName()), LAST_NAME_NOT_VALID)
                .validate(form -> Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$",
                        form.getEmail()), EMAIL_NOT_VALID)
                .validate(form -> Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!]{5,15}$",
                        form.getPassword()), PASSWORD_NOT_VALID)
                .validate(form -> Pattern.matches("^(?=.*[a-z])[a-zA-Z\\d]{2,50}$",
                        form.getUsername()), USERNAME_NOT_VALID);
    }

    public Validator registrationDtoUsernameAndEmailDuplicateValidation(UserRegistrationDto registrationDto){

        return Validator.of(registrationDto)
                .validate(form -> !userService.findByEmail(form.getEmail()).isPresent(), EMAIL_ALREADY_IN_DB)
                .validate(form -> !userService.findByUserName(form.getUsername()).isPresent(), USERNAME_ALREADY_IN_DB);
    }

}
