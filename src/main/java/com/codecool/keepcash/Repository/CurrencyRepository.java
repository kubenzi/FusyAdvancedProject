package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
