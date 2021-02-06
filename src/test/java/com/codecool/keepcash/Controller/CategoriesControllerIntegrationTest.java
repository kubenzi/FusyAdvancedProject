package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Service.Category.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = CategoryControllerIntegrationTestConfiguration.class)
//@WebAppConfiguration
//public class CategoriesControllerIntegrationTest {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void init() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    public void should_return_all_categories_by_user_id() throws Exception {
//        // given:
//        Long id = 1L;
//        List<CategoryDto> categoryDtos = new ArrayList<>();
//        categoryDtos.add(new CategoryDto(1L, "first", Collections.emptyList()));
//        categoryDtos.add(new CategoryDto(2L, "second", Collections.emptyList()));
//        categoryDtos.add(new CategoryDto(3L, "third", Collections.emptyList()));
//        when(categoryService.getCategoriesDtoByUserId(id)).thenReturn(categoryDtos);
//
//        // when, then:
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/v1//users/{userId}/categories"))
//                .andExpect(status().isOk());
//    }
//
//}
