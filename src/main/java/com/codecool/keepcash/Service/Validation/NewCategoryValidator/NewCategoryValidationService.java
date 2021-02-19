package com.codecool.keepcash.Service.Validation.NewCategoryValidator;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Exception.NewCategoryValidationException;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.Service.Validation.ValidationError;
import com.codecool.keepcash.Service.Validation.Validator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codecool.keepcash.Service.Validation.ValidationError.*;

@Service
public class NewCategoryValidationService {

    private final CategoryService categoryService;

    private List<ValidationError> errorList = new ArrayList<>();

    public NewCategoryValidationService(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    public boolean isCategoryDtoValid(NewCategoryDto newCategoryDto, Long userId) {

        Validator newCategoryDataValid = isNewCategoryDataValid(newCategoryDto);
        List<ValidationError> errors = newCategoryDataValid.getErrors();

        List<ValidationError> categoryDtoNameAvailable = isCategoryDtoNameAvailable(newCategoryDto, userId);

        errors.addAll(categoryDtoNameAvailable);

        if (!errors.isEmpty()) {
            throw new NewCategoryValidationException(errors.toString());
        } else return true;
    }


    private Validator isNewCategoryDataValid(NewCategoryDto newCategoryDto) {

        return Validator.of(newCategoryDto)
                .validate(form -> form.getName() != null && !form.getName().equalsIgnoreCase("null"),
                        AT_LEAST_ONE_VALUE_IS_NULL)
                .validate(form -> form.getName().length() < 250, TO_LONG_NAME);
    }

    private List<ValidationError> isCategoryDtoNameAvailable(NewCategoryDto newCategoryDto, Long userId) {

        List<ValidationError> errors = new ArrayList<>();

        List<CategoryDto> categoriesDtoByUserId = categoryService.getCategoriesDtoByUserId(userId);

        List<CategoryDto> categoryDtoNames = categoriesDtoByUserId.stream()
                .filter(categoryDto -> categoryDto.getName().trim().equalsIgnoreCase(newCategoryDto.getName()))
                .collect(Collectors.toList());

        if (!categoryDtoNames.isEmpty()) {
            errors.add(NAME_ALREADY_IN_DB);
        }

        return errors;

    }

}
