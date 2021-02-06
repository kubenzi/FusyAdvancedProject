package com.codecool.keepcash.Service.CSV;

import au.com.bytecode.opencsv.CSVReader;
import com.codecool.keepcash.Entity.Operation;
import com.codecool.keepcash.Service.Operation.OperationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceING implements CSVService {

    private OperationService operationService;

    public CSVServiceING(OperationService operationService) {
        this.operationService = operationService;
    }

    public List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException {
        byte[] fileContent = file.getBytes();

        String parsedFile = new String(fileContent, "windows-1250");
        List<String[]> operationArrays = parseCSVToListOfStringArrays(readCSVFile(parsedFile));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<Operation> parsed = new ArrayList<>();
        for (String[] array : operationArrays) {
            Operation operation = new Operation(array[2],
                    Double.parseDouble(array[8].replace(',', '.')),
                    formatter.parse(array[0]));
            parsed.add(operation);
        }

        return parsed;
    }

    private List<String[]> readCSVFile(String fileContent) throws IOException {
        CSVReader csvReader = new CSVReader(new StringReader(fileContent));
        List<String[]> list = csvReader.readAll();
        csvReader.close();

        return list;
    }

    private List<String[]> parseCSVToListOfStringArrays(List<String[]> fileContent) {
        List<String[]> filtered = fileContent.stream()
                .filter(row -> row[0].length() != 0)
                .filter(row -> Character.isDigit(row[0].charAt(1)))
                .map(row -> row[0] + "," + row[1])
                .map(row -> row.split(";"))
                .collect(Collectors.toList());

        return filtered;
    };
}
