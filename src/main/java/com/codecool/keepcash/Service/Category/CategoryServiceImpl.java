package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.Service.User.UserService;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private UserService userService;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> maybeCategory = categoryRepository.findById(id);

        if (maybeCategory.isPresent()) {
            return maybeCategory.get();
        } else {
            throw new IdNotFoundException(id, Category.class.getSimpleName());
        }
    }

    @Override
    public Category getCategoryByNameAndUserId(String name, Long userId) {
        Optional<Category> maybeCategory = categoryRepository.findByNameAndUserId(name, userId);

        if (maybeCategory.isPresent()) {
            return maybeCategory.get();
        } else {
            throw new IdNotFoundException("No " + name + " category for given user or user not found.");
        }
    }

    @Override
    public CategoryDto getCategoryDtoById(Long id) {
        return CategoryToCategoryDtoConverter
                .convertToDto(getCategoryById(id));
    }

    @Override
    public List<CategoryDto> getCategoriesDtoByUserId(Long userId) {
        return CategoryToCategoryDtoConverter.convertListToDto(
                userService.getUserDataById(userId).getCategories());
    }

    @Override
    public List<Category> createStartingCategories() {
        Category other = new Category("OTHER", true);
        Category income = new Category("INCOME", true);
        Category unassigned = new Category("UNASSIGNED", true);

        return Arrays.asList(other, income, unassigned);
    }

    @Override
    @Transactional
    public void addCategory(Long userId, NewCategoryDto newCategoryDto, boolean isBuiltin) {
        Category newCategory = CategoryDtoToCategoryConverter.convertNewCategoryDtoToCategory(newCategoryDto);
        newCategory.setBuiltin(isBuiltin);
        categoryRepository.save(newCategory);

        UserData userData = userService.getUserDataById(userId);
        userData.getCategories().add(newCategory);
        userService.saveUpdatedUserData(userData);
    }

    @Override
    public void saveUpdatedCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(id, Category.class.getSimpleName());
        }
    }
}
