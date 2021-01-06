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

    @GetMapping("/user/{userId}/categories")
    @ResponseStatus(OK)
    public List<CategoryDto> getCategories(@PathVariable Long userId){
        return categoryService.getCategoriesByUserId(userId);
    }

    @GetMapping("/user/{userId}/categories/{categoryId}")
    @ResponseStatus(OK)
    public CategoryDto getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(Long.valueOf(categoryId));
    }

    @PostMapping("/user/{userId}/categories")
    @ResponseStatus(CREATED)
    public void addCategory(@PathVariable Long userId,
                            @RequestBody NewCategoryDto newCategoryDto) {
        categoryService.addCategory(userId, newCategoryDto);
    }

    @DeleteMapping("/user/{userId}/categories/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategory(@PathVariable String categoryId){
        categoryService.deleteCategoryById(Long.valueOf(categoryId));
    }

}
