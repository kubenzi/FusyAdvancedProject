package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.Service.Validation.NewCategoryValidator.NewCategoryValidationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    private CategoryService categoryService;
    private NewCategoryValidationService newCategoryValidationService;

    public CategoriesController(CategoryService categoryService,
                                NewCategoryValidationService newCategoryValidationService) {
        this.categoryService = categoryService;
        this.newCategoryValidationService = newCategoryValidationService;
    }

    @GetMapping("/users/{userId}/categories")
    @ResponseStatus(OK)
    public List<CategoryDto> getCategoriesByUserId(@PathVariable Long userId) {
        return categoryService.getCategoriesDtoByUserId(userId);
    }


    @GetMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(OK)
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {

        return categoryService.getCategoryDtoById(categoryId);
    }

    @PostMapping("/users/{userId}/categories")
    @ResponseStatus(CREATED)
    public void addNewCategoryForUserId(@PathVariable Long userId,
                                        @RequestBody NewCategoryDto newCategoryDto) {
        if (newCategoryValidationService.isCategoryDtoValid(newCategoryDto, userId)) {
            categoryService.addCategory(userId, newCategoryDto, false);
        }

    }

    @DeleteMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategoryById(@PathVariable Long categoryId,
                                   @PathVariable Long userId) {

        categoryService.deleteCategoryById(categoryId, userId);
    }
}
