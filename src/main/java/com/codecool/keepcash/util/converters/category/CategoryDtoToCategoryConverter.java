package com.codecool.keepcash.util.converters.category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.util.converters.operation.OperationDtoToOperationConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoToCategoryConverter {

    public CategoryDtoToCategoryConverter() {
    }

    public static Category convertDtoToCategory(CategoryDto categoryDto) {
        return new Category(categoryDto.getName(),
                OperationDtoToOperationConverter.convertDtoListToOperationList(categoryDto.getOperations()
                ));
    }

    public static Category convertNewCategoryDtoToCategory(NewCategoryDto newCategoryDto) {
        return new Category(newCategoryDto.getName());
    }

    public static List<Category> convertDtoToList(List<CategoryDto> categories) {
        return categories.stream().
                map(categoryDto -> convertDtoToCategory(categoryDto)).
                collect(Collectors.toList());
    }
}
