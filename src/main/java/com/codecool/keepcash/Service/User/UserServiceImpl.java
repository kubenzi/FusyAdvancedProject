package com.codecool.keepcash.Service.User;

import com.codecool.keepcash.Dto.User.NewEmailDto;
import com.codecool.keepcash.Dto.User.UserDataDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.util.converters.user.UserDataToUserDataDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            return userDataToUserDataDtoConverter.convertToDto(
                    userDataRepository.findById(id).
                            orElseThrow(() -> new IdNotFoundException(id, User.class.getSimpleName()))
            );
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, UserData.class.getSimpleName());
        }
    }

    @Override
//    @Transactional
    public void deleteUserById(Long id) {
        try {
            userDataRepository.deleteById(id);
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public void updateUserEmail(Long userId, NewEmailDto newEmailDto) {
        try {
            if (!newEmailDto.getNewEmail().isEmpty()) {
                userDataRepository.findById(userId).get().setEmail(newEmailDto.getNewEmail());
            }
        } catch (NullPointerException e) {
            throw new IdNotFoundException(userId, User.class.getSimpleName());
        }
    }
}
