package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.User;


public interface UserService {

    User getUserById(Long id);

    User addUser(User user);
}
