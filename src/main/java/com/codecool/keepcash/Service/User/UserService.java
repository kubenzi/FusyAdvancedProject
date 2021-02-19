package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.NewPasswordDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface UserService {
    UserData getUserDataById(Long id);

    UserDataDto getUserDataDtoById(Long id);

    void deleteUserById(Long id);

    void saveUpdatedUserData(UserData userData);

    void updateUserEmail(Long userId, NewEmailDto newEmailDto);

    void updateUserPassword(NewPasswordDto newPasswordDto);

    User findById(Long userId);

    Optional<User> findByUserName(String username);

    Optional<UserData> findByEmail(String email);

    Double calculateTotalBalanceInPLN(Long userId) throws InterruptedException, IOException, URISyntaxException;
}
