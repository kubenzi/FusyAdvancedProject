package com.codecool.keepcash.Service;

import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Repository.UserRepository;

public interface UserService {

    User getUserById(Long id);
}
