package com.codecool.keepcash.Controller;

import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Service.CSV.CSVImplementer;
import com.codecool.keepcash.Service.CSV.CSVService;
import com.codecool.keepcash.Service.CSV.CSVServiceINGImpl;
import com.codecool.keepcash.Service.CSV.CSVServiceMilleniumImpl;
import com.codecool.keepcash.Service.Operation.OperationService;
import com.codecool.keepcash.Service.User.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CSVController {

    private CSVService CSVService;
    private CSVService CSVServiceMillenium;
    private OperationService operationService;

    public CSVController(CSVServiceINGImpl CSVService,
                         CSVServiceMilleniumImpl CSVServiceMillenium,
                         OperationService operationService) {
        this.CSVService = CSVService;
        this.CSVServiceMillenium = CSVServiceMillenium;
        this.operationService = operationService;
    }

    @PostMapping(value = "/users/{userId}/accounts/{accountId}/post-from-csv",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(CREATED)
    public void addNewOperationsFromCSV(@RequestParam("file") MultipartFile file,
                                        @PathVariable("userId") Long userId,
                                        @PathVariable("accountId") Long accountId) throws IOException, ParseException {

        List<Operation> operations =  CSVImplementer.execute(new CSVServiceINGImpl(), file);

        operationService.addNewCSVOperations(operations, userId, accountId);
    }
}
