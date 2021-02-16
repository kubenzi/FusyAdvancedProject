package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserDataDtoConverter {

    public UserDataToUserDataDtoConverter() {
    }

    public static UserDataDto convertToDto(UserData userData) {
        return new UserDataDto(userData.getId(),
                userData.getFirstName(),
                userData.getFirstName(),
                userData.getEmail(),
                CategoryToCategoryDtoConverter.convertListToDto(userData.getCategories()),
                AccountToAccountDtoConverter.convertListToDto(userData.getAccounts())
        );
    }
}
