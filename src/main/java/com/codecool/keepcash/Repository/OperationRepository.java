package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {

    @Query(value = "SELECT * FROM operations WHERE category_id = ?1", nativeQuery = true)
    List<Operation> findByCategoryId(Long categoryId);
}
