package com.codecool.keepcash.Service.Authentication;

import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.Service.Validation.NewUserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private final AccountService accountService;

    private final CategoryService categoryService;

    private final NewUserValidationService newUserValidationService;

    public AuthenticationService(AccountService accountService,
                                 CategoryService categoryService,
                                 NewUserValidationService newUserValidationService) {
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.newUserValidationService = newUserValidationService;
    }

    public void register(UserRegistrationDto userRegistrationDto) {

        if (newUserValidationService.isNewUserDataValid(userRegistrationDto)) {
            createNewUser(userRegistrationDto);
        }
    }

    public Authentication login(UserCredentialsDto userCredentialsDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialsDto.getUsername(),
                userCredentialsDto.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with username %s not found.", username)));
    }

    @Transactional
    public void createNewUser(UserRegistrationDto userRegistrationDto) {
        User user = new User(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                userRegistrationDto.getUsername());

        userRepository.save(user);

        UserData userData = new UserData(userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                user);

        List<Category> inbuiltCategories = categoryService.createStartingCategories();
        inbuiltCategories.forEach(category -> userData.getCategories().add(category));

        userData.getAccounts().add(accountService.createBuiltinAccounts());
        userDataRepository.save(userData);
    }
}
