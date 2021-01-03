package com.codecool.keepcash.util.converters.user;

import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.util.converters.account.AccountToAccountDtoConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter {

    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter;
    private AccountToAccountDtoConverter accountToAccountDtoConverter;

    public UserToUserDtoConverter(CategoryToCategoryDtoConverter categoryToCategoryDtoConverter,
                                  AccountToAccountDtoConverter accountToAccountDtoConverter) {
        this.categoryToCategoryDtoConverter = categoryToCategoryDtoConverter;
        this.accountToAccountDtoConverter = accountToAccountDtoConverter;
    }

    public UserDto convertToDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getFirstName(),
                user.getEmail(),
                user.getUsername(),
                categoryToCategoryDtoConverter.convertListToDto(user.getCategories()),
                accountToAccountDtoConverter.convertListToDto(user.getAccounts())
        );
    }
}
