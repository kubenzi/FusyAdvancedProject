package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Authentication.LoginData;
import com.codecool.keepcash.Dto.Authentication.UserCredentialsDto;
import com.codecool.keepcash.Dto.Authentication.UserRegistrationDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Service.Authentication.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public LoginData login(@RequestBody UserCredentialsDto userCredentialsDto) {
        Authentication authentication = authenticationService.login(userCredentialsDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User loggedUser = (User) authentication.getPrincipal();
        return new LoginData(loggedUser.getId(),
                loggedUser.getUsername(),
                RequestContextHolder.currentRequestAttributes().getSessionId());
    }

    @PostMapping(value = "/log-out")
    @ResponseStatus(OK)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }
}
