package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.AccountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@Sql("db_data_for_test.sql")
@DataJpaTest
public class AccountTypeRepositoryTest {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Test
    public void dummy_method_should_find_account_with_id() {
        //given
        Long id = 1L;

        //when
        AccountType accountType = accountTypeRepository.findAccountTypeById(id);

        //then
        assertEquals(accountType.getName(), "Savings");
    }

}
