package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Service.Authentication.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthController {
    private AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void register(@RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.register(userRegistrationDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public void login(@RequestBody UserCredentialsDto userCredentialsDto) {
        Authentication authentication = authenticationService.login(userCredentialsDto);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
