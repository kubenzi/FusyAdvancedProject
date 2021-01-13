//package com.codecool.keepcash.Controller;
//
//import com.codecool.keepcash.Dto.Account.AccountTypeDto;
//import com.codecool.keepcash.Dto.Currency.CurrencyDto;
//import com.codecool.keepcash.Dto.Operation.OperationDto;
//import com.codecool.keepcash.Service.Account.AccountTypeService;
//import com.codecool.keepcash.Service.Currency.CurrencyService;
//import com.codecool.keepcash.Service.Operation.OperationService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.HttpStatus.CREATED;
//import static org.springframework.http.HttpStatus.NO_CONTENT;
//
//@RestController
//@RequestMapping("/api/v1")
//public class TestController {
//
//    private OperationService operationService;
//    private CurrencyService currencyService;
//    private AccountTypeService accountTypeService;
//
//
//    public TestController(OperationService operationService,
//                          CurrencyService currencyService, AccountTypeService accountTypeService) {
//        this.operationService = operationService;
//        this.currencyService = currencyService;
//        this.accountTypeService = accountTypeService;
//    }
//
//    @GetMapping("/currencies")
//    public List<CurrencyDto> getCurrencies() {
//        return currencyService.getAllCurrencies();
//    }
//
//    @PostMapping("/currencies")
//    @ResponseStatus(CREATED)
//    public void addCurrency(@RequestBody CurrencyDto currencyDto) {
//        currencyService.addNewCurrency(currencyDto);
//    }
//
//    @GetMapping("/account-types")
//    public List<AccountTypeDto> getAccountTypes(@RequestParam(required = false) String sortBy){
//        return sortBy!= null ?
//                accountTypeService.getAllAccountTypesSortByName(sortBy) :
//                accountTypeService.getAllAccountTypes();
//    }
//
//    @PostMapping("/account-types")
//    @ResponseStatus(CREATED)
//    public void  addAccountType(@RequestBody AccountTypeDto accountTypeDto) {
//        accountTypeService.addAccountType(accountTypeDto);
//    }
//
//    @DeleteMapping("/account-types/{id}")
//    @ResponseStatus(NO_CONTENT)
//    public void deleteAccountType(@PathVariable String id) {
//        accountTypeService.deleteAccountTypeById(Long.valueOf(id));
//    }
//
//    @GetMapping("/account-types/{id}")
//    public AccountTypeDto getAccountTypeById(@PathVariable String id){
//        return accountTypeService.getAccountTypeById(Long.valueOf(id));
//    }
//
//    @PutMapping("/account-types/{id}")
//    @ResponseStatus(CREATED)
//    public void updateAccountType(@PathVariable String id,
//                                   @RequestBody AccountTypeDto accountTypeDto){
//        accountTypeService.updateAccountType(Long.valueOf(id), accountTypeDto);
//    }
//}
