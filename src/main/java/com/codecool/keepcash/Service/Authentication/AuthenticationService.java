package com.codecool.keepcash.Service.Authentication;

import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.NewUserDataException;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.Service.Validation.ValidationError;
import com.codecool.keepcash.Service.Validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AccountService accountService;

    private CategoryService categoryService;

    private ValidationService validationService;

    public AuthenticationService(AccountService accountService,
                                 CategoryService categoryService,
                                 ValidationService validationService) {
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.validationService = validationService;
    }

    public void register(UserRegistrationDto userRegistrationDto) {


        if (newUserDataValidation(userRegistrationDto).size() != 0) {
            throw new NewUserDataException(
                    newUserDataValidation(userRegistrationDto).stream().map(error -> error.toString())
                            .collect(Collectors.joining(", "))
            );
        } else {

            User user = new User(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                    userRegistrationDto.getUsername());
            failIfUserAlreadyRegistered(userRegistrationDto.getUsername());
            userRepository.save(user);

            UserData userData = new UserData(userRegistrationDto.getFirstName(),
                    userRegistrationDto.getLastName(),
                    userRegistrationDto.getEmail(),
                    user);

            List<Category> inbuiltCategories = categoryService.createBuiltinCategories();
            inbuiltCategories.stream().forEach(category -> userData.getCategories().add(category));

            userData.getAccounts().add(accountService.createBuiltinAccounts());
            userDataRepository.save(userData);
        }
    }

    private List<ValidationError> newUserDataValidation(UserRegistrationDto userRegistrationDto) {
        List<List<ValidationError>> listsOfErrors = new ArrayList<>();

        listsOfErrors.add(validationService.registrationDtoNullValidation(userRegistrationDto).getErrors());

        if (listsOfErrors.size() == 0) {
            listsOfErrors.add(validationService.registrationDtoDataValidation(userRegistrationDto).getErrors());
            listsOfErrors.add(validationService.registrationDtoUsernameAndEmailDuplicateValidation(userRegistrationDto)
                    .getErrors());
        }

//        listsOfErrors.add(validationService.registrationDtoNullValidation(userRegistrationDto).getErrors());
//        listsOfErrors.add(validationService.registrationDtoDataValidation(userRegistrationDto).getErrors());
//        listsOfErrors.add(validationService.registrationDtoUsernameAndEmailDuplicateValidation(userRegistrationDto)
//                .getErrors());

        return listsOfErrors.stream().flatMap(errors -> errors.stream()).collect(Collectors.toList());
    }

    public Authentication login(UserCredentialsDto userCredentialsDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDto.getUsername(),
                userCredentialsDto.getPassword()));
    }

    private void failIfUserAlreadyRegistered(String username) {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isPresent()) {
            try {
                throw new ValidationException("User already exist: " + maybeUser.get().getUsername());
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with username %s not found.", username)));
    }
}
