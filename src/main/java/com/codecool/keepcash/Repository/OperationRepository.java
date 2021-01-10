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

    @Query(value = "SELECT * FROM operations WHERE account_id = ?1", nativeQuery = true)
    List<Operation> findByAccountId(Long accountId);

    @Query(value = "select * from operations inner join accounts a on a.id = operations.account_id" +
            " inner join user_data ud on ud.user_id = a.user_id where a.user_id = ?1", nativeQuery = true)
    List<Operation> findAllByUserId(Long userId);

}
