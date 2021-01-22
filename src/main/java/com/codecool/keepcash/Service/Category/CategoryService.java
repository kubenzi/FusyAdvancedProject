package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    CategoryDto getCategoryDtoById(Long id);

    List<CategoryDto> getCategoriesDtoByUserId(Long userId);

    void addCategory(Long userId, NewCategoryDto newCategoryDto);

    void saveUpdatedCategory(Category category);

    void deleteCategoryById(Long id);
}
