package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.util.converters.account.AccountDtoToAccountConverter;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;

public class UserDtoToUserConverter {

    private CategoryDtoToCategoryConverter categoryDtoToCategoryConverter;
    private AccountDtoToAccountConverter accountDtoToAccountConverter;

    public UserDtoToUserConverter(CategoryDtoToCategoryConverter categoryDtoToCategoryConverter,
                                  AccountDtoToAccountConverter accountDtoToAccountConverter) {
        this.categoryDtoToCategoryConverter = categoryDtoToCategoryConverter;
        this.accountDtoToAccountConverter = accountDtoToAccountConverter;
    }

    public User convertDtoToUser(UserDto userDto){
        return new User(
                String.valueOf(userDto.getId()),
                userDto.getFirstName(),
                userDto.getFirstName(),
                userDto.getEmail(),
                userDto.getUsername(),
                categoryDtoToCategoryConverter.convertDtoToList(userDto.getCategories()),
                accountDtoToAccountConverter.convertDtoToList(userDto.getAccounts())
        );
    }
}
