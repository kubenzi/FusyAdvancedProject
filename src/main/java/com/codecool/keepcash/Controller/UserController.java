package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(OK)
    public UserDataDto getUserData(@PathVariable Long id){
        return userService.getUserDataById(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(CREATED)
    public void updateUserEmail(@PathVariable Long id, @RequestBody NewEmailDto newEmailDto) {
        userService.updateUserEmail(id, newEmailDto);
    }
}
