package com.codecool.keepcash.Service.Account;

import com.codecool.keepcash.Dto.Account.AccountDto;
import com.codecool.keepcash.Dto.Account.NewAccountDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountRepository;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountDtoToAccountConverter accountDtoToAccountConverter;
    private AccountToAccountDtoConverter accountToAccountDtoConverter;
    private AccountRepository accountRepository;
    private UserDataRepository userDataRepository;


    public AccountServiceImpl(AccountDtoToAccountConverter accountDtoToAccountConverter,
                              AccountToAccountDtoConverter accountToAccountDtoConverter,
                              AccountRepository accountRepository,
                              UserDataRepository userDataRepository) {
        this.accountDtoToAccountConverter = accountDtoToAccountConverter;
        this.accountToAccountDtoConverter = accountToAccountDtoConverter;
        this.accountRepository = accountRepository;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public void createNewAccount(NewAccountDto newAccountDto, Long userId) {
        Account newAccount = accountDtoToAccountConverter.convertNewAccountToAccount(newAccountDto);
        accountRepository.save(newAccount);

        UserData userData = userDataRepository.findById(userId).get();
        userData.getAccounts().add(newAccount);
        userDataRepository.save(userData);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        return accountToAccountDtoConverter.convertToDto(
                accountRepository.findById(accountId)
                        .orElseThrow(() -> new IdNotFoundException(
                                accountId, Account.class.getSimpleName()))

        );
    }

    @Override
    public List<AccountDto> getAccountsByUserId(Long userId) {
        return accountToAccountDtoConverter.convertListToDto(
                userDataRepository.findById(userId).get().getAccounts()
        );
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
