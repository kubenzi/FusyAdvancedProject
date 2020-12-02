package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@Sql("db_data_for_tests.sql")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void get_all_users() {
        //given
        int listSize = 2;

        //when
        List<User> userList = (List<User>) userRepository.findAll();

        //then
        assertEquals(userList.size(), listSize);
    }
}
