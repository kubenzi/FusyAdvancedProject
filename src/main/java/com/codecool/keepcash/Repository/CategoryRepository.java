package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories WHERE name = ?1 AND user_id = ?2 AND builtin = true", nativeQuery = true)
    Optional<Category> findByNameAndUserId(String name, Long userId);

    @Query(value = "SELECT category_id FROM categories JOIN operations o on categories.id = o.category_id WHERE o.id = ?1", nativeQuery = true)
    Long findCategoryIdByOperationId(Long operationId);


}
