package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Exceptions.IdNotFoundException;
import com.codecool.keepcash.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id){
        User user = userRepository.findUserById(id);
//        if(user == null){
//            throw new IdNotFoundException(id);
//        }
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


}
