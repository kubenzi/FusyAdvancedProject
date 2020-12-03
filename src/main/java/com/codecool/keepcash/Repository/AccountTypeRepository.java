package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {

}
