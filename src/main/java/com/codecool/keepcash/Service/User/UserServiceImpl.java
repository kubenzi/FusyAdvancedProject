package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.util.converters.user.UserDataToUserDataDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserDataRepository userDataRepository;
    UserDataToUserDataDtoConverter userDataToUserDataDtoConverter;

    public UserServiceImpl(UserRepository userRepository,
                           UserDataRepository userDataRepository,
                           UserDataToUserDataDtoConverter userDataToUserDataDtoConverter) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
        this.userDataToUserDataDtoConverter = userDataToUserDataDtoConverter;
    }

    @Override
    public UserDataDto getUserDataById(Long id) {
        return userDataToUserDataDtoConverter.convertToDto(
                userDataRepository.findById(id).
                        orElseThrow(() -> new IdNotFoundException(id, User.class.getSimpleName()))
        );
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException(id, User.class.getSimpleName());
        }
    }
}
