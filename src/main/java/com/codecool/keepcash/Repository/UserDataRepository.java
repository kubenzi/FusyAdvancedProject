package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.User;
import com.codecool.keepcash.Entity.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);
}
