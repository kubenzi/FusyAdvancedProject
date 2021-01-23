package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Operation.NewOperationDto;
import com.codecool.keepcash.Dto.Operation.OperationDto;
import com.codecool.keepcash.Service.Operation.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1")
public class OperationsController {

    private OperationService operationService;

    public OperationsController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/users/{userId}/operations")
    public List<OperationDto> getOperationsByGivenParameter(@PathVariable Long userId,
                                                            @RequestParam(required = false) Long categoryId,
                                                            @RequestParam(required = false) Long accountId) {
        if (categoryId == null && accountId == null) {
            return operationService.getAllOperationsByUserId(userId);
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
    public void createNewOperation(@RequestBody NewOperationDto operationDto) {
        operationService.addTransaction(operationDto);
    }

}


