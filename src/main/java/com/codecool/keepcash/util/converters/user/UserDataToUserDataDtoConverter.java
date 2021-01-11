package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserDataDtoConverter {

    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter;
    private AccountToAccountDtoConverter accountToAccountDtoConverter;

    public UserDataToUserDataDtoConverter(CategoryToCategoryDtoConverter categoryToCategoryDtoConverter,
                                          AccountToAccountDtoConverter accountToAccountDtoConverter) {
        this.categoryToCategoryDtoConverter = categoryToCategoryDtoConverter;
        this.accountToAccountDtoConverter = accountToAccountDtoConverter;
    }

    public UserDataDto convertToDto(UserData userData) {
        return new UserDataDto(userData.getId(),
                userData.getFirstName(),
                userData.getFirstName(),
                userData.getEmail(),
                categoryToCategoryDtoConverter.convertListToDto(userData.getCategories()),
                accountToAccountDtoConverter.convertListToDto(userData.getAccounts())
        );
    }
}
