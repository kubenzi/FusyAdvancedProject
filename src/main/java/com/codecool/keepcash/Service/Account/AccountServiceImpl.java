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
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private UserService userService;
    private AccountTypeService accountTypeService;
    private CurrencyService currencyService;


    public AccountServiceImpl(AccountRepository accountRepository,
                              UserService userService,
                              AccountTypeService accountTypeService,
                              CurrencyService currencyService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.accountTypeService = accountTypeService;
        this.currencyService = currencyService;
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
    @Transactional
    public void addNewAccount(NewAccountDto newAccountDto, Long userId) {
        AccountType accountType = accountTypeService.getAccountTypeById(newAccountDto.getAccountTypeId());
        Currency currency = currencyService.getCurrencyById(newAccountDto.getCurrencyId());

        Account newAccount = AccountDtoToAccountConverter
                .convertNewAccountToAccount(newAccountDto, accountType, currency);
        accountRepository.save(newAccount);

        UserData userData = userService.getUserDataById(userId);
        userData.getAccounts().add(newAccount);
        userService.saveUpdatedUserData(userData);
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
}
