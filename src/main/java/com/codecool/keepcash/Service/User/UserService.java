package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.UserDataDto;

public interface UserService {

    UserDataDto getUserDataById(Long id);
    void deleteUserById(Long id);
    void updateUserEmail(Long userId, NewEmailDto newEmailDto);
}
