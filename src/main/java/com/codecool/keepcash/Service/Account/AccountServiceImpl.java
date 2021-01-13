package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountRepository;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import com.codecool.keepcash.Repository.CurrencyRepository;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private UserDataRepository userDataRepository;
    private AccountTypeRepository accountTypeRepository;
    private CurrencyRepository currencyRepository;


    public AccountServiceImpl(AccountRepository accountRepository,
                              UserDataRepository userDataRepository,
                              AccountTypeRepository accountTypeRepository,
                              CurrencyRepository currencyRepository) {
        this.accountRepository = accountRepository;
        this.userDataRepository = userDataRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    @Transactional
    public void createNewAccount(NewAccountDto newAccountDto, Long userId) {
        Account newAccount = AccountDtoToAccountConverter.convertNewAccountToAccount(
                newAccountDto, accountTypeRepository, currencyRepository);
        accountRepository.save(newAccount);

        Optional<UserData> maybeCurrentUser = userDataRepository.findById(userId);
        if (maybeCurrentUser.isPresent()) {
            UserData currentUser = maybeCurrentUser.get();
            currentUser.getAccounts().add(newAccount);
            userDataRepository.save(currentUser);
        } else {
            throw new IdNotFoundException(userId, UserData.class.getSimpleName());
        }
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        return AccountToAccountDtoConverter.convertToDto(
                accountRepository.findById(accountId)
                        .orElseThrow(() -> new IdNotFoundException(
                                accountId, Account.class.getSimpleName()))

        );
    }

    @Override
    public List<AccountDto> getAccountsByUserId(Long userId) {
        UserData userData = userDataRepository.findById(userId).orElseThrow(
                () -> new IdNotFoundException(
                        userId, UserData.class.getSimpleName())
        );

        return AccountToAccountDtoConverter.convertListToDto(userData.getAccounts());
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
