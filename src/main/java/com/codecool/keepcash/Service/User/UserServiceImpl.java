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

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserDataRepository userDataRepository;
    private UserDataToUserDataDtoConverter userDataToUserDataDtoConverter;

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
    public void deleteUserById(Long id) {
        try {
            userDataRepository.deleteById(id);
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public void updateUserEmail(Long userId, NewEmailDto newEmailDto) {

        try {
            UserData currentUserData = userDataRepository.findById(userId).get();
            if (!newEmailDto.getNewEmail().isEmpty() &&
                    newEmailDto.getOldEmail().trim().equals(currentUserData.getEmail().trim()) &&
                    !newEmailDto.getNewEmail().trim().equals(currentUserData.getEmail().trim())) {
                currentUserData.setEmail(newEmailDto.getNewEmail().trim());
                userDataRepository.save(currentUserData);
            }
        } catch (NullPointerException e) {
            throw new IdNotFoundException(userId, User.class.getSimpleName());
        }
    }
}
