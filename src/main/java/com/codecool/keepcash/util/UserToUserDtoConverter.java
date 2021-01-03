package com.codecool.keepcash.util;

import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter {

    public UserToUserDtoConverter() {
    }

    public UserDto convertUserToUserDto(User user){
        return new UserDto(
                user.getFirstName(),
                user.getFirstName(),
                user.getEmail(),
                user.getUsername(),
                user.getCategories(),
                user.getAccounts()
        );
    }

}
