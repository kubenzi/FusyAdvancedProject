package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;

public class UserDataDtoToUserConverter {

    private CategoryDtoToCategoryConverter categoryDtoToCategoryConverter;
    private AccountDtoToAccountConverter accountDtoToAccountConverter;

    public UserDataDtoToUserConverter(CategoryDtoToCategoryConverter categoryDtoToCategoryConverter,
                                  AccountDtoToAccountConverter accountDtoToAccountConverter) {
        this.categoryDtoToCategoryConverter = categoryDtoToCategoryConverter;
        this.accountDtoToAccountConverter = accountDtoToAccountConverter;
    }

    public UserData convertDtoToUserData(UserDataDto userDataDto){
        return new UserData(userDataDto.getId(),
                userDataDto.getFirstName(),
                userDataDto.getFirstName(),
                userDataDto.getEmail(),
                categoryDtoToCategoryConverter.convertDtoToList(userDataDto.getCategories()),
                accountDtoToAccountConverter.convertDtoToList(userDataDto.getAccounts())
        );
    }
}
