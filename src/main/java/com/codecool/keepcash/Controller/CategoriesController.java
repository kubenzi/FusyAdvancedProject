package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import org.springframework.web.bind.annotation.*;
import com.codecool.keepcash.Service.Category.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    public CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/users/{userId}/categories")
    @ResponseStatus(OK)
    public List<CategoryDto> getCategoriesByUserId(@PathVariable Long userId){
        return categoryService.getCategoriesByUserId(userId);
    }

    @GetMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(OK)
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/users/{userId}/categories")
    @ResponseStatus(CREATED)
    public void addNewCategoryForUserId(@PathVariable Long userId,
                            @RequestBody NewCategoryDto newCategoryDto) {
        categoryService.addCategory(userId, newCategoryDto);
    }

    @DeleteMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategoryById(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
    }

}
