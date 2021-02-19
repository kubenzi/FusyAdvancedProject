package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Entity.Currency;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountRepository;
import com.codecool.keepcash.Service.Currency.CurrencyService;
import com.codecool.keepcash.Service.User.UserService;
import com.codecool.keepcash.Service.Validation.NewAccountFormValidationService;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final AccountTypeService accountTypeService;
    private final CurrencyService currencyService;
    private final NewAccountFormValidationService newAccountFormValidationService;


    public AccountServiceImpl(AccountRepository accountRepository,
                              UserService userService,
                              AccountTypeService accountTypeService,
                              CurrencyService currencyService,
                              NewAccountFormValidationService newAccountFormValidationService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.accountTypeService = accountTypeService;
        this.currencyService = currencyService;
        this.newAccountFormValidationService = newAccountFormValidationService;
    }

    @Override
    public Account getAccountById(Long accountId) {
        Optional<Account> maybeAccount = accountRepository.findById(accountId);

        if (maybeAccount.isPresent()) {
            return maybeAccount.get();
        } else {
            throw new IdNotFoundException(accountId, Account.class.getSimpleName());
        }
    }

    @Override
    public AccountDto getAccountDtoById(Long accountId) {
        return AccountToAccountDtoConverter
                .convertToDto(getAccountById(accountId));
    }

    @Override
    public List<AccountDto> getAccountsDtoByUserId(Long userId) {
        UserData userData = userService.getUserDataById(userId);

        return AccountToAccountDtoConverter.convertListToDto(userData.getAccounts());
    }

    @Override
    public Account createBuiltinAccounts() {
        return new Account("CASH PLN",
                0.0,
                "00 0000 0000 0000 0000",
                accountTypeService.getAccountTypeById(2L),
                currencyService.getCurrencyById(2L),
                new Date(System.currentTimeMillis()),
                true);
    }

    @Override
    @Transactional
    public void addNewAccount(NewAccountDto newAccountDto, Long userId) {
        prepareNewAccountDtoToDbUpload(newAccountDto);

        if (newAccountFormValidationService.isNewAccountFormValid(newAccountDto)) {
            AccountType accountType = accountTypeService.getAccountTypeById(newAccountDto.getAccountTypeId());
            Currency currency = currencyService.getCurrencyById(newAccountDto.getCurrencyId());

            Account newAccount = AccountDtoToAccountConverter
                    .convertNewAccountToAccount(newAccountDto, accountType, currency);
            newAccount.setCreationDate(new Date(System.currentTimeMillis()));
            accountRepository.save(newAccount);

            UserData userData = userService.getUserDataById(userId);
            userData.getAccounts().add(newAccount);
            userService.saveUpdatedUserData(userData);
        }
    }

    @Override
    public void saveUpdatedAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Long accountId) {
        try {
            accountRepository.deleteById(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(accountId, Account.class.getSimpleName());
        }
    }

    @Override
    public Double getBalanceByAccountId(Long accountId) {
        return getAccountDtoById(accountId).getBalance();
    }

    private void prepareNewAccountDtoToDbUpload(NewAccountDto newAccountDto) {
        DecimalFormat balanceFormat = new DecimalFormat("#.##");

        if (newAccountDto.getAccountNumber() == null || newAccountDto.getAccountNumber().trim().isEmpty()) {
            newAccountDto.setAccountNumber("00 0000 0000 0000 0000");
        }

        newAccountDto.setBalance(Double.valueOf(balanceFormat.format(newAccountDto.getBalance())));
    }
}
