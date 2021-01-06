package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.UserDataDto;

public interface UserService {

    UserDataDto getUserDataById(Long id);
    void deleteUserById(Long id);
}
