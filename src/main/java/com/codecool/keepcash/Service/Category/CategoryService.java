package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    Category getCategoryByNameAndUserId(String name, Long userId);

    CategoryDto getCategoryDtoById(Long id);

    List<CategoryDto> getCategoriesDtoByUserId(Long userId);

    List<Category> createStartingCategories();

    void addCategory(Long userId, NewCategoryDto newCategoryDto, boolean isBuiltin);

    void saveUpdatedCategory(Category category);

    void deleteCategoryById(Long id);
}
