package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.CredentialsDto;
import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Service.AuthenticationService;
import com.codecool.keepcash.Service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthController {
    private UserService userService;
    private AuthenticationService authenticationService;

    public AuthController(UserService userService,
                          AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void register(@RequestBody UserDto userDto) {
        authenticationService.register(userDto);
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public void login(@RequestBody CredentialsDto credentialsDto) {

    }
}
