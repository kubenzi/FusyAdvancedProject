package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Dto.Account.AccountTypeDto;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Service.Operation.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class OperationsController {

    private OperationService operationService;

    public OperationsController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/operations")
    public List<Operation> getOperationsByCategory(@RequestParam Long categoryId) {
        return operationService.getAllOperationsById(categoryId);
    }

}


//    @GetMapping("/account-types")
//    public List<AccountTypeDto> getAccountTypes(@RequestParam(required = false) String sortBy){
//        return sortBy!= null ?
//                accountTypeService.getAllAccountTypesSortByName(sortBy) :
//                accountTypeService.getAllAccountTypes();
//    }
//    http://localhost:8080/api/v1/operations?categoryId=10
