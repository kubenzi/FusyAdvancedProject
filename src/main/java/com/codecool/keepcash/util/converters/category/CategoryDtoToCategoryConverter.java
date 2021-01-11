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

    private OperationDtoToOperationConverter operationDtoToOperationConverter;

    public CategoryDtoToCategoryConverter(OperationDtoToOperationConverter operationDtoToOperationConverter) {
        this.operationDtoToOperationConverter = operationDtoToOperationConverter;
    }

    public Category convertDtoToCategory(CategoryDto categoryDto) {
        return new Category(categoryDto.getName(),
                operationDtoToOperationConverter.convertDtoListToOperationList(categoryDto.getOperations()
                ));
    }

    public Category convertNewCategoryDtoToCategory(NewCategoryDto newCategoryDto) {
        return new Category(newCategoryDto.getName());
    }

    public List<Category> convertDtoToList(List<CategoryDto> categories) {
        return categories.stream().
                map(this::convertDtoToCategory).
                collect(Collectors.toList());
    }
}
