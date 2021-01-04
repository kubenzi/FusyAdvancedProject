package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.CategoryDto;
import com.codecool.keepcash.Dto.OperationDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    void addCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long id);
}
