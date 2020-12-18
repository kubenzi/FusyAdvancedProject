package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new IdNotFoundException(id, User.class.getSimpleName()));
    }
}
