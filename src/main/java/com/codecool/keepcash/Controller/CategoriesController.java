package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Category;
import com.sun.net.httpserver.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codecool.keepcash.Service.Category.CategoryService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    private CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/users/{userId}/categories")
    @ResponseStatus(OK)
    public List<CategoryDto> getCategoriesByUserId(@PathVariable Long userId){
        return categoryService.getCategoriesDtoByUserId(userId);
    }

//    public ResponseEntity<?> categoryByIdAccess(Long categoryId, Long userId) {
//        List<CategoryDto> categoriesByUserId = getCategoriesByUserId(userId);
//        CategoryDto categoryDtoById = categoryService.getCategoryDtoById(categoryId);
//        boolean contains = categoriesByUserId.contains(categoryDtoById);
//        if (contains)
//            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
//        else
//            return new ResponseEntity<Error>(FORBIDDEN);
//    }

    @GetMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(OK)
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
//        List<CategoryDto> categoriesByUserId = getCategoriesByUserId(userId);
//        CategoryDto categoryDtoById = categoryService.getCategoryDtoById(categoryId);
//        boolean contains = categoriesByUserId.contains(categoryDtoById);
//        if(contains){
//            return categoryDtoById;
//        }
//        return categoryByIdAccess(categoryId, userId);
        return categoryService.getCategoryDtoById(categoryId);
    }

    @PostMapping("/users/{userId}/categories")
    @ResponseStatus(CREATED)
    public void addNewCategoryForUserId(@PathVariable Long userId,
                            @RequestBody NewCategoryDto newCategoryDto) {
        categoryService.addCategory(userId, newCategoryDto, false);
    }

    @DeleteMapping("/users/{userId}/categories/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategoryById(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
    }
}
