package com.codecool.keepcash.Service.CSV;

import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceINGImpl implements CSVService {

    public CSVServiceINGImpl() {
    }

    @Override
    public List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException {
        byte[] fileContent = file.getBytes();
        String parsedFile = new String(fileContent, "windows-1250");
        List<String[]> operationArrays = parseCSVToListOfStringArrays(readCSVFile(parsedFile));

        return createListOfOperations(operationArrays);
    }

    @Override
    public List<String[]> parseCSVToListOfStringArrays(List<String[]> fileContent) {
        List<String[]> filtered = fileContent.stream()
                .filter(row -> row[0].length() != 0)
                .filter(row -> Character.isDigit(row[0].charAt(1)))
                .map(row -> row[0] + "," + row[1])
                .map(row -> row.split(";"))
                .collect(Collectors.toList());

        return filtered;
    }

    @Override
    public Operation createOperationFromSingleRow(String[] row) throws ParseException {
        int operationDateIndex = 0;
        int operationNameIndex = 2;
        int operationValueIndex = 8;
        int operationCurrencyIndex = 9;

        // ING .csv operation value separator is ',' - it has to be replaced with '.'
        // date format defined in interface field
        Operation operation = new Operation(row[operationNameIndex],
                Double.parseDouble(row[operationValueIndex].replace(',', '.')),
                formatter.parse(row[operationDateIndex]));

        return operation;
    }
}
