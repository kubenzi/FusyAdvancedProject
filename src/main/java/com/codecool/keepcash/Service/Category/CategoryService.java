package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCategoriesByUserId(Long userId);
    CategoryDto getCategoryById(Long id);
    void addCategory(Long userId, NewCategoryDto newCategoryDto);
    void deleteCategoryById(Long id);
}
