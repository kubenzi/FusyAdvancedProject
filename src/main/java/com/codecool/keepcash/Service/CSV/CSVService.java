package com.codecool.keepcash.Service.CSV;

import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CSVService {

    List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException;

}
