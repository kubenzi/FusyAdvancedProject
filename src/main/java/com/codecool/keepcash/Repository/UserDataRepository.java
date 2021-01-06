package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {
}
