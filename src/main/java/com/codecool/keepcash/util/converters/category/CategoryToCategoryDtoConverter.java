package com.codecool.keepcash.util.converters.category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryToCategoryDtoConverter {

    public CategoryToCategoryDtoConverter() {
    }

    public static CategoryDto convertToDto(Category category) {
        return new CategoryDto(category.getId(),
                category.getName(),
                OperationToOperationDtoConverter.convertListToDto(category.getOperations()));
    }

    public static List<CategoryDto> convertListToDto(List<Category> categoriesList) {
        return categoriesList.stream().
                map(category -> convertToDto(category)).
                collect(Collectors.toList());
    }
}
