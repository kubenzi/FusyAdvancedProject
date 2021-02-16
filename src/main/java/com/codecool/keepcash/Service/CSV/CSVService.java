package com.codecool.keepcash.Service.CSV;

import au.com.bytecode.opencsv.CSVReader;
import com.codecool.keepcash.Entity.Operation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface CSVService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException;

    List<String[]> parseCSVToListOfStringArrays(List<String[]> fileContent);

    Operation createOperationFromSingleRow(String[] row) throws ParseException;

    default List<Operation> createListOfOperations(List<String[]> operationArrays) throws ParseException {
        List<Operation> newOperations = new ArrayList<>();

        for (String[] row : operationArrays) {
            Operation operation = createOperationFromSingleRow(row);
            newOperations.add(operation);
        }

        return newOperations;
    }

    default List<String[]> readCSVFile(String fileContent) throws IOException {
        CSVReader csvReader = new CSVReader(new StringReader(fileContent));
        List<String[]> list = csvReader.readAll();
        csvReader.close();

        return list;
    }

}
