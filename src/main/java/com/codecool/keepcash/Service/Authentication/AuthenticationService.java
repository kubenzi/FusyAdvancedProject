package com.codecool.keepcash.Service.Authentication;

import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public void register(UserRegistrationDto userRegistrationDto) {
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
