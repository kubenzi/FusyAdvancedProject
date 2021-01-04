package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.CategoryDto;
import org.springframework.web.bind.annotation.*;
import com.codecool.keepcash.Service.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    public CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(Long.valueOf(id));
    }

    @PostMapping("/categories")
    @ResponseStatus(CREATED)
    public void addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.addCategory(categoryDto);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategoryById(Long.valueOf(id));
    }

}
