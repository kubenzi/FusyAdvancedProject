package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.NewEmailFormException;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class EmailUpdateFormValidationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public EmailUpdateFormValidationService() {
    }

    public boolean isNewEmailFormValid(NewEmailDto newEmailForm,
                                       Long userId) {
        List<ValidationError> validationErrors = validateNewEmailForm(newEmailForm, userId);

        if (validationErrors.size() != 0) {
            throw new NewEmailFormException(
                    validationErrors.stream().map(error -> error.name())
                            .collect(Collectors.joining(", "))
            );
        }

        return true;
    }

    private List<ValidationError> validateNewEmailForm(NewEmailDto newEmailForm,
                                                       Long userId) {
        List<List<ValidationError>> listOfErrors = new ArrayList<>();

        listOfErrors.add(checkNewEmailFormForNullFields(newEmailForm).getErrors());
        List<ValidationError> nullErrors = listOfErrors.get(0);

        if (nullErrors.size() == 0) {
            listOfErrors.add(checkNewEmailFormData(newEmailForm, userId).getErrors());
            listOfErrors.add(checkIfNewEmailAlreadyInDatabase(newEmailForm).getErrors());
        }

        return listOfErrors.stream()
                .flatMap(errors -> errors.stream()).collect(Collectors.toList());
    }

    private Validator checkNewEmailFormForNullFields(NewEmailDto newEmailDto) {
        return Validator.of(newEmailDto)
                .validate(form -> form.getOldEmail() != null && !form.getOldEmail().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getOldEmail() != null && !form.getOldEmail().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getOldEmail() != null && !form.getOldEmail().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getOldEmail() != null && !form.getOldEmail().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL);
    }

    private Validator checkNewEmailFormData(NewEmailDto newEmailDto,
                                            Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String password = userDetails.getPassword();

        UserData userData = userService.getUserDataById(userId);

        return Validator.of(newEmailDto)
                .validate(form -> form.getOldEmail().equals(userData.getEmail()),
                        OLD_EMAIL_DOESNT_MATCH)
                .validate(form -> Pattern.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$",
                        form.getNewEmail()), EMAIL_NOT_VALID)
                .validate(form -> form.getNewEmail().equals(form.getNewEmailConfirmed()),
                        NEW_EMAIL_DOESNT_MATCH_CONFIRMED)
                .validate(form -> bCryptPasswordEncoder.matches(form.getPassword(), password),
                        WRONG_PASSWORD_FOR_GIVEN_USER);
    }

    private Validator checkIfNewEmailAlreadyInDatabase(NewEmailDto newEmailDto) {

        return Validator.of(newEmailDto)
                .validate(form -> !userService.findByEmail(form.getNewEmail()).isPresent(),
                        EMAIL_ALREADY_IN_DB)
                .validate(form -> !form.getOldEmail().equals(form.getNewEmail()),
                        NEW_EMAIL_SAME_AS_OLD_EMAIL);
    }
}
