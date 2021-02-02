package com.codecool.keepcash.Service.Authentication;

import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
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
    private AuthenticationManager authenticationManager;

    public void register(UserRegistrationDto userRegistrationDto) {
        User user = new User(bCryptPasswordEncoder.encode(userRegistrationDto.getPassword()),
                userRegistrationDto.getUsername());
        failIfUserAlreadyRegistered(userRegistrationDto.getUsername());
        userRepository.save(user);

        Long registeredId = userRepository.findByUsername(user.getUsername()).get().getId();

        UserData userData = new UserData(userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                user);
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
        return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException
                        (String.format("User with username %s not found.", username)));
    }
}
