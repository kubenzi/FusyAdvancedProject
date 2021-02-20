package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Exception.NewOperationDtoValidationException;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Service.Validation.NewOperationValidation.NewOperationDtoService;
import com.codecool.keepcash.Service.Validation.ValidationError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class OperationsController {

    private OperationService operationService;
    private NewOperationDtoService newOperationDtoService;

    public OperationsController(OperationService operationService, NewOperationDtoService newOperationDtoService) {
        this.operationService = operationService;
        this.newOperationDtoService = newOperationDtoService;
    }

    @GetMapping("/users/{userId}/operations")
    public List<OperationDto> getOperationsByGivenParameter(@PathVariable Long userId,
                                                            @RequestParam(required = false) Long categoryId,
                                                            @RequestParam(required = false) Long accountId,
                                                            @RequestParam(required = false) Integer lastOperation) {

        if (categoryId == null && accountId == null && lastOperation == null) {
            return operationService.getAllOperationsByUserId(userId);
        }
        if (lastOperation != null) {
            return operationService.getLastOperation(userId, lastOperation);
        }
        return categoryId != null ?
                operationService.getAllOperationByCategoryId(categoryId) :
                operationService.getAllOperationByAccountId(accountId);
    }

    @DeleteMapping("/users/{userId}/operations/{operationId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteOperations(@PathVariable Long operationId) {
        operationService.deleteOperationsById(operationId);
    }

    @PostMapping("/users/{userId}/operations")
    @ResponseStatus(CREATED)
    public void createNewOperation(@RequestBody NewOperationDto operationDto, @PathVariable Long userId) {

        List<ValidationError> validationErrors = newOperationDtoService.validateNewOperationDto(operationDto);
        if (validationErrors.isEmpty()) {
            operationService.addTransaction(operationDto, userId);
        } else {
            throw new NewOperationDtoValidationException(
                    validationErrors.toString()
            );
        }


    }

}


