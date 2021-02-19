package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(OK)
    public UserDataDto getUserData(@PathVariable Long id){
        return userService.getUserDataDtoById(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PostMapping("/users/{id}/update-email")
    @ResponseStatus(CREATED)
    public void updateUserEmail(@PathVariable Long id, @RequestBody NewEmailDto newEmailDto) {
        userService.updateUserEmail(id, newEmailDto);
    }

    @GetMapping("/users/{id}/get-total-balance")
    @ResponseStatus(OK)
    public Double getTotalBalance(@PathVariable Long id) throws InterruptedException, IOException, URISyntaxException {
        return userService.calculateTotalBalanceInPLN(id);
    }
}
