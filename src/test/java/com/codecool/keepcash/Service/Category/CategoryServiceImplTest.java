package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.Service.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

//

    @Test
    public void should_delete_category() {
        // given:
        Long id = 1L;

        // when:
        categoryServiceImpl.deleteCategoryById(id);

        // then:
        verify(categoryRepository, times(1)).deleteById(eq(id));
    }

    @Test(expected = IdNotFoundException.class)
    public void should_throw_exception_when_category_to_be_deleted_not_found() {
        // given:
        Long id = 3345L;
        doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(id);

        // when:
        categoryServiceImpl.deleteCategoryById(id);
    }

    @Test
    public void same_as_above_but_with_assertJ() {
        // given:
        Long id = 3345L;
        doThrow(EmptyResultDataAccessException.class).when(categoryRepository).deleteById(id);

        // when:
        Throwable exception = catchThrowable(() -> categoryServiceImpl.deleteCategoryById(id));

        // then:
        assertThat(exception).isInstanceOf(IdNotFoundException.class)
                .hasMessage("Id= " + id + " not found for " + Category.class.getSimpleName());
    }
}
