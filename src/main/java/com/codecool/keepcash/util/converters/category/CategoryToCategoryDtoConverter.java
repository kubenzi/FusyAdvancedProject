package com.codecool.keepcash.util.converters.category;

import com.codecool.keepcash.Dto.AccountTypeDto;
import com.codecool.keepcash.Dto.CategoryDto;
import com.codecool.keepcash.Entity.AccountType;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.util.converters.operation.OperationToOperationDtoConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryToCategoryDtoConverter {

    private OperationToOperationDtoConverter operationToOperationDtoConverter;

    public CategoryToCategoryDtoConverter(OperationToOperationDtoConverter operationToOperationDtoConverter) {
        this.operationToOperationDtoConverter = operationToOperationDtoConverter;
    }

    public CategoryDto convertToDto(Category category) {
        return new CategoryDto(category.getId(),
                category.getName(),
                operationToOperationDtoConverter.convertListToDto(category.getOperations()));
    }

    public List<CategoryDto> convertListToDto(List<Category> categoriesList) {
        return categoriesList.stream().
                map(this::convertToDto).
                collect(Collectors.toList());
    }
}
