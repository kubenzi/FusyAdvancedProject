package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;

public class UserDataDtoToUserConverter {

    public UserDataDtoToUserConverter() {
    }

    public static UserData convertDtoToUserData(UserDataDto userDataDto) {
        return new UserData(userDataDto.getId(),
                userDataDto.getFirstName(),
                userDataDto.getFirstName(),
                userDataDto.getEmail(),
                CategoryDtoToCategoryConverter.convertDtoToList(userDataDto.getCategories()),
                AccountDtoToAccountConverter.convertDtoToList(userDataDto.getAccounts())
        );
    }
}
