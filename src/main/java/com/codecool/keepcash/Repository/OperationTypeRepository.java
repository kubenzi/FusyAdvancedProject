package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.OperationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends CrudRepository<OperationType, Long> {
}
