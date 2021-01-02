package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.CredentialsDto;
import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
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
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void register(UserDto userDto) {
        User user = new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()),
                userDto.getUsername());
        failIfUserAlreadyRegistered(userDto.getUsername());
        userRepository.save(user);
    }

    public Authentication login(CredentialsDto credentialsDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentialsDto.getUsername(),
                credentialsDto.getPassword()));
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
