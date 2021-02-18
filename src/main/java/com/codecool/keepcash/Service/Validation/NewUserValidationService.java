package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Exception.NewUserDataException;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class NewUserValidationService {

    private final UserService userService;

    public NewUserValidationService(UserService userService) {
        this.userService = userService;
    }

    public boolean isNewUserDataValid(UserRegistrationDto userRegistrationDto) throws NewUserDataException {
        List<ValidationError> validationErrors = newUserDataValidation(userRegistrationDto);

        if (validationErrors.size() != 0) {
            throw new NewUserDataException(
                    validationErrors.stream().map(error -> error.name())
                            .collect(Collectors.joining(", "))
            );
        }

        return true;
    }

    private List<ValidationError> newUserDataValidation(UserRegistrationDto userRegistrationDto) {
        List<List<ValidationError>> listsOfErrors = new ArrayList<>();

        listsOfErrors.add(registrationDtoNullValidation(userRegistrationDto).getErrors());
        List<ValidationError> nullErrors = listsOfErrors.get(0);

        if (nullErrors.size() == 0) {
            listsOfErrors.add(registrationDtoDataValidation(userRegistrationDto).getErrors());
            listsOfErrors.add(registrationDtoUsernameAndEmailDuplicateValidation(userRegistrationDto)
                    .getErrors());
        }

        return listsOfErrors.stream().flatMap(errors -> errors.stream()).collect(Collectors.toList());
    }

    private Validator registrationDtoNullValidation(UserRegistrationDto registrationDto) {

        return Validator.of(registrationDto)
                .validate(form -> form.getFirstName() != null && !form.getFirstName().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getLastName() != null && !form.getLastName().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getUsername() != null && !form.getUsername().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getPassword() != null && !form.getPassword().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getEmail() != null && !form.getEmail().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL);
    }

    private Validator registrationDtoDataValidation(UserRegistrationDto registrationDto) {
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

    private Validator registrationDtoUsernameAndEmailDuplicateValidation(UserRegistrationDto registrationDto){

        return Validator.of(registrationDto)
                .validate(form -> !userService.findByEmail(form.getEmail()).isPresent(), EMAIL_ALREADY_IN_DB)
                .validate(form -> !userService.findByUserName(form.getUsername()).isPresent(), USERNAME_ALREADY_IN_DB);
    }

}
