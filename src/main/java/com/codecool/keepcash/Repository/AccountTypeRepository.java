package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
