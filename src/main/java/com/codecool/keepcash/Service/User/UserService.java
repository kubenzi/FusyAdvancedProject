package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;

import java.util.Optional;

public interface UserService {
    UserData getUserDataById(Long id);
    UserDataDto getUserDataDtoById(Long id);
    void deleteUserById(Long id);
    void saveUpdatedUserData(UserData userData);
    void updateUserEmail(Long userId, NewEmailDto newEmailDto);
    Optional<User> findByUserName(String username);
    Optional<UserData> findByEmail(String email);
}
