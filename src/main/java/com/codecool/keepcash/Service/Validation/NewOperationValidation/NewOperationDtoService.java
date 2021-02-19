package com.codecool.keepcash.Service.Validation.NewOperationValidation;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Entity.Account;
import com.codecool.keepcash.Entity.Category;
import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Service.Account.AccountService;
import com.codecool.keepcash.Service.Category.CategoryService;
import com.codecool.keepcash.Service.Validation.ValidationError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codecool.keepcash.Service.Validation.ValidationError.NO_ACCOUNT_IN_DB;
import static com.codecool.keepcash.Service.Validation.ValidationError.NO_CATEGORY_IN_DB;

@Service
public class NewOperationDtoService {


    private CategoryService categoryService;
    private AccountService accountService;

    public NewOperationDtoService(CategoryService categoryService,
                                  AccountService accountService) {
        this.categoryService = categoryService;
        this.accountService = accountService;
    }

    List<ValidationError> validationResult = new ArrayList<>();

    private NewOperationNullValidator getNewOperationNullValidator() {

        return new NewOperationNullValidator(
                new NewOperationDescriptionValidator(null)
        );
    }

    public List<ValidationError> validateNewOperationDto(NewOperationDto newOperationDto) {
        clearErrors();
        validateCategoryId(newOperationDto);
        validateAccountId(newOperationDto);
        return getNewOperationNullValidator().validate(newOperationDto, validationResult);
    }

    private void validateCategoryId(NewOperationDto newOperationDto) {
        try {
            Category categoryById = categoryService.getCategoryById(newOperationDto.getCategoryId());
        } catch (RuntimeException e) {
            validationResult.add(NO_CATEGORY_IN_DB);
        }
    }

    private void validateAccountId(NewOperationDto newOperationDto) {
        try {
            Account accountById = accountService.getAccountById(newOperationDto.getAccountId());
        } catch (RuntimeException e) {
            validationResult.add(NO_ACCOUNT_IN_DB);
        }
    }

    public void clearErrors(){
        validationResult.clear();
    }
}
