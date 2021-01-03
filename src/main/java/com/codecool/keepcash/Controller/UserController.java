package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Service.UserService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(Long.valueOf(id));
    }

    @PostMapping("/users")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody User user){
        userService.addUser(user);
    }
}
