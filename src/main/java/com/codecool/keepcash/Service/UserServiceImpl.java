package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.UserDto;
import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.UserRepository;
import com.codecool.keepcash.util.converters.user.UserToUserDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserToUserDtoConverter userToUserDtoConverter;

    public UserServiceImpl(UserRepository userRepository, UserToUserDtoConverter userToUserDtoConverter) {
        this.userRepository = userRepository;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }

    @Override
    public UserDto getUserById(Long id) {
        return userToUserDtoConverter.convertToDto(
                userRepository.findById(id).
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

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
