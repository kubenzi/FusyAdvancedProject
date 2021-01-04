package com.codecool.keepcash.Service;

import com.codecool.keepcash.Dto.CategoryDto;
import com.codecool.keepcash.Dto.OperationDto;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.CategoryRepository;
import com.codecool.keepcash.util.converters.category.CategoryDtoToCategoryConverter;
import com.codecool.keepcash.util.converters.category.CategoryToCategoryDtoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryToCategoryDtoConverter categoryToCategoryDtoConverter;
    private CategoryDtoToCategoryConverter categoryDtoToCategoryConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryDtoConverter categoryToCategoryDtoConverter, CategoryDtoToCategoryConverter categoryDtoToCategoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryDtoConverter = categoryToCategoryDtoConverter;
        this.categoryDtoToCategoryConverter = categoryDtoToCategoryConverter;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();

        return categoryToCategoryDtoConverter.convertListToDto(allCategories);
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
    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryDtoToCategoryConverter.convertDtoToCategory(categoryDto));
    }

    @Override
    public void deleteCategoryById(Long id) {
        try{
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new IdNotFoundException(id, Category.class.getSimpleName());
        }
    }
}
