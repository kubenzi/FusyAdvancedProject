package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Currency;
import com.codecool.keepcash.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT record FROM users record WHERE record.id = ?1")
    User findUserById(Long id);
}

