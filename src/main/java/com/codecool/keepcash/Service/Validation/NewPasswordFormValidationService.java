package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.User.NewPasswordDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Exception.InvalidNewPasswordDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class NewPasswordFormValidationService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public NewPasswordFormValidationService() {
    }

    public boolean isNewPasswordFormValid(NewPasswordDto newPasswordDto) {
        List<ValidationError> validationErrors = validateNewPasswordDto(newPasswordDto);

        if (validationErrors.size() != 0) {
            throw new InvalidNewPasswordDtoException(
                    validationErrors.stream().map(error -> error.name())
                    .collect(Collectors.joining(", "))
            );
        }

        return true;
    }

    private List<ValidationError> validateNewPasswordDto(NewPasswordDto newPasswordDto) {
        List<List<ValidationError>> listOfErrors = new ArrayList<>();

        listOfErrors.add(checkNewPasswordDtoForNullFields(newPasswordDto).getErrors());
        List<ValidationError> nullErrors = listOfErrors.get(0);

        if (nullErrors.size() == 0) {
            listOfErrors.add(checkNewPasswordDtoData(newPasswordDto).getErrors());
        }

        return listOfErrors.stream()
                .flatMap(errors -> errors.stream()).collect(Collectors.toList());
    }

    private Validator checkNewPasswordDtoForNullFields(NewPasswordDto newPasswordDto) {
        return Validator.of(newPasswordDto)
                .validate(form -> form.getNewPassword() != null &&
                                !form.getNewPassword().equalsIgnoreCase("null"), AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getNewPasswordConfirmed() != null &&
                                !form.getNewPasswordConfirmed().equalsIgnoreCase("null"), AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getOldPassword() != null &&
                                !form.getOldPassword().equalsIgnoreCase("null"), AT_LEAST_ONE_VALUE_IS_NULL);
    }

    private Validator checkNewPasswordDtoData(NewPasswordDto newPasswordDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentPassword = user.getPassword();

        return Validator.of(newPasswordDto)
                .validate(form -> bCryptPasswordEncoder.matches(form.getOldPassword(), currentPassword),
                        WRONG_PASSWORD_FOR_GIVEN_USER)
                .validate(form -> form.getNewPassword().equals(form.getNewPasswordConfirmed()),
                        NEW_PASSWORD_DOESNT_MATCH_CONFIRMED_PASSWORD)
                .validate(form -> Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!]{5,15}$",
                        form.getNewPassword()), PASSWORD_NOT_VALID);
    }
}
