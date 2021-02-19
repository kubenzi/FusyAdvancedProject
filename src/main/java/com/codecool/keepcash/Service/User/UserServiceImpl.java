package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.NewPasswordDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.ExternalApis.Client.ExchangeRatesClient;
import com.codecool.keepcash.ExternalApis.Dto.Rates;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.Service.Validation.NewEmailFormValidationService;
import com.codecool.keepcash.Service.Validation.NewPasswordFormValidationService;
import com.codecool.keepcash.util.converters.user.UserDataToUserDataDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private NewEmailFormValidationService newEmailFormValidationService;

    @Autowired
    private NewPasswordFormValidationService newPasswordFormValidationService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final ExchangeRatesClient exchangeRatesClient;

    public UserServiceImpl(UserRepository userRepository,
                           UserDataRepository userDataRepository,
                           ExchangeRatesClient exchangeRatesClient) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
        this.exchangeRatesClient = exchangeRatesClient;
    }

    @Override
    public UserData getUserDataById(Long id) {
        Optional<UserData> maybeUser = userDataRepository.findById(id);

        if (maybeUser.isPresent()) {
            return maybeUser.get();
        } else {
            throw new IdNotFoundException(id, UserData.class.getSimpleName());
        }
    }

    @Override
    public UserDataDto getUserDataDtoById(Long id) {
        return UserDataToUserDataDtoConverter.convertToDto(getUserDataById(id));
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userDataRepository.deleteById(id);
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public void saveUpdatedUserData(UserData userData) {
        userDataRepository.save(userData);
    }

    @Override
    public void updateUserEmail(Long userId, NewEmailDto newEmailForm) {
        UserData userData = getUserDataById(userId);

        if (newEmailFormValidationService.isNewEmailFormValid(newEmailForm, userId)) {
            userData.setEmail(newEmailForm.getNewEmail());
            userDataRepository.save(userData);
        }
    }

    @Override
    public void updateUserPassword(NewPasswordDto newPasswordDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (newPasswordFormValidationService.isNewPasswordFormValid(newPasswordDto)) {
            user.setPassword(bCryptPasswordEncoder.encode(newPasswordDto.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public User findById(Long userId) {
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isPresent()) {
            return maybeUser.get();
        } else {
            throw new IdNotFoundException(userId, User.class.getSimpleName());
        }
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        return userDataRepository.findByEmail(email);
    }

    @Override
    public Double calculateTotalBalanceInPLN(Long userId) throws InterruptedException, IOException, URISyntaxException {
        UserData currentUser = getUserDataById(userId);
        Rates rates = exchangeRatesClient.getCurrencies().getRates();

        DecimalFormat balanceFormat = new DecimalFormat("#.##");

        Double reducedBalance = currentUser.getAccounts().stream()
                .map(account -> account.getBalance() / (rates.createMapOfRates().get(account.getCurrency().getSignature())))
                .reduce(0.0, (a, b) -> a + b);

        return Double.valueOf(balanceFormat.format(reducedBalance));
    }
}
