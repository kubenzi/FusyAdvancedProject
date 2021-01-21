package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.Service.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    // happy path
    @Test
    public void should_delete_category() {
        // given:
        Long id = 1L;
        // when:
        categoryServiceImpl.deleteCategoryById(id);
        // then:
        verify(categoryRepository, times(1)).deleteById(eq(id));
    }
}
