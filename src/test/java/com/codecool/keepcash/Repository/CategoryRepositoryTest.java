package com.codecool.keepcash.Repository;

import com.codecool.keepcash.Entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql("db_data_for_test.sql")
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void should_save_category() {
        // given:
        Category first = new Category("first", false);

        // when:
        categoryRepository.save(first);

        //then:
        assertTrue(categoryRepository
                .findById(first.getId())
                .isPresent());
    }

    @Test
    public void should_delete_category() {
        // given:
        Long id = 1L;
        Category category = categoryRepository.findById(id).get();

        // when:
        categoryRepository.delete(category);

        // then:
        assertTrue(!categoryRepository.findById(id).isPresent());
    }

    @Test
    public void should_return_all_categories() {
        // given:
        int expectedSize = 10;

        // when:
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();

        // then:
        assertEquals(expectedSize, categoryList.size());
    }
}
