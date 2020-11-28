package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
}
