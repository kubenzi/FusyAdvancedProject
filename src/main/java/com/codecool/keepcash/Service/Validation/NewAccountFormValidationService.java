package com.codecool.keepcash.Service.Validation;

import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.InvalidNewAccountDtoException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.Repository.CurrencyRepository;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class NewAccountFormValidationService {

    private final AccountTypeRepository accountTypeRepository;

    private final CurrencyRepository currencyRepository;

    private final UserService userService;

    public NewAccountFormValidationService(AccountTypeRepository accountTypeRepository,
                                           CurrencyRepository currencyRepository,
                                           UserService userService) {
        this.accountTypeRepository = accountTypeRepository;
        this.currencyRepository = currencyRepository;
        this.userService = userService;
    }

    public boolean isNewAccountFormValid(NewAccountDto newAccountDto, Long userId) {
        List<ValidationError> validationErrors = validateNewAccountForm(newAccountDto, userId);

        if (validationErrors.size() != 0) {
            throw new InvalidNewAccountDtoException (
                    validationErrors.stream().map(error -> error.name())
                            .collect(Collectors.joining(", "))
            );
        }

        return true;
    }

    private List<ValidationError> validateNewAccountForm(NewAccountDto newAccountDto, Long userId) {
        List<List<ValidationError>> listOfErrors = new ArrayList<>();

        listOfErrors.add(checkNewAccountFormForNullFields(newAccountDto, userId).getErrors());
        List<ValidationError> nullErrors = listOfErrors.get(0);

        if (nullErrors.size() == 0) {
            listOfErrors.add(checkNewEmailFormData(newAccountDto).getErrors());
            listOfErrors.add(checkIfCurrencyAndAccountTypeAlreadyInDb(newAccountDto).getErrors());
        }

        return listOfErrors.stream()
                .flatMap(errors -> errors.stream()).collect(Collectors.toList());
    }

    private Validator checkNewAccountFormForNullFields(NewAccountDto newAccountForm, Long userId) {
        UserData userData = userService.getUserDataById(userId);
        List<Account> filteredByName = userData.getAccounts().stream()
                .filter(account -> account.getName().trim().equalsIgnoreCase(newAccountForm.getName()))
                .collect(Collectors.toList());

        return Validator.of(newAccountForm)
                .validate(form -> form.getName() != null && !form.getName().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> filteredByName.isEmpty(), ACCOUNT_NAME_ALREADY_USED_FOR_CURRENT_USER)
                .validate(form -> form.getBalance() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getAccountTypeId() != null, AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getCurrencyId() != null, AT_LEAST_ONE_VALUE_IS_NULL);
    }

    private Validator checkNewEmailFormData(NewAccountDto newAccountForm) {
        return Validator.of(newAccountForm)
                .validate(form -> Pattern.matches("^[a-zA-Z0-9 _-]{2,15}$", form.getName()), ACCOUNT_NAME_NOT_VALID)
                .validate(form -> Pattern.matches("^[0-9]{0,18}$", form.getAccountNumber().replaceAll(" ", "")),
                        ACCOUNT_NUMBER_NOT_VALID)
                .validate(form -> Double.valueOf(form.getBalance()) > -10000000.0 && Double.valueOf(form.getBalance()) < 10000000.0,
                        ACCOUNT_BALANCE_NOT_WITHIN_ALLOWED_RANGE);
    }

    private Validator checkIfCurrencyAndAccountTypeAlreadyInDb(NewAccountDto newAccountForm) {
        return Validator.of(newAccountForm)
                .validate(form -> accountTypeRepository.findById(newAccountForm.getAccountTypeId()).isPresent(),
                        ACCOUNT_TYPE_WITH_GIVEN_ID_NOT_PRESENT_IN_DB)
                .validate(form -> currencyRepository.findById(newAccountForm.getCurrencyId()).isPresent(),
                        CURRENCY_WITH_GIVEN_ID_NOT_PRESENT_IN_DB);
    }
}
