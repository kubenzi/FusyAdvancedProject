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

public abstract class CSVImplementer implements CSVService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public abstract List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException;

    public abstract List<String[]> parseCSVToListOfStringArrays(List<String[]> fileContent);

    public abstract Operation createOperationFromSingleRow(String[] row) throws ParseException;


    protected List<Operation> createListOfOperations(List<String[]> operationArrays) throws ParseException {
        List<Operation> newOperations = new ArrayList<>();

        for (String[] row : operationArrays) {
            Operation operation = createOperationFromSingleRow(row);
            newOperations.add(operation);
        }

        return newOperations;
    }

    protected List<String[]> readCSVFile(String fileContent) throws IOException {
        CSVReader csvReader = new CSVReader(new StringReader(fileContent));
        List<String[]> list = csvReader.readAll();
        csvReader.close();

        return list;
    }

    public static List<Operation> execute(CSVImplementer CSVService, MultipartFile file) throws IOException, ParseException {
        return CSVService.parseCSVToOperations(file);
    }
}
