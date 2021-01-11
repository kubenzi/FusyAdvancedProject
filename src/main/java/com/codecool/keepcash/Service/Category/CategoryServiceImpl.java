package com.codecool.keepcash.Service.Category;

import com.codecool.keepcash.Dto.Category.CategoryDto;
import com.codecool.keepcash.Dto.Category.NewCategoryDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.UserData;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.Repository.UserDataRepository;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserDataRepository userDataRepository;

    private CategoryRepository categoryRepository;
    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter;
    private CategoryDtoToCategoryConverter categoryDtoToCategoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryToCategoryDtoConverter categoryToCategoryDtoConverter,
                               CategoryDtoToCategoryConverter categoryDtoToCategoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryDtoConverter = categoryToCategoryDtoConverter;
        this.categoryDtoToCategoryConverter = categoryDtoToCategoryConverter;
    }

    @Override
    public List<CategoryDto> getCategoriesByUserId(Long userId) {
        return categoryToCategoryDtoConverter.convertListToDto(
                userDataRepository.findById(userId).get().getCategories());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryToCategoryDtoConverter.convertToDto(
                categoryRepository.findById(id).
                        orElseThrow(() -> new IdNotFoundException(
                                id, Category.class.getSimpleName())
                        ));
    }

    @Override
    @Transactional
    public void addCategory(Long userId, NewCategoryDto newCategoryDto) {
        Category newCategory = categoryDtoToCategoryConverter.convertNewCategoryDtoToCategory(newCategoryDto);
        categoryRepository.save(newCategory);

        UserData userData = userDataRepository.findById(userId).get();
        userData.getCategories().add(newCategory);
        userDataRepository.save(userData);
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
