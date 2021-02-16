package com.codecool.keepcash.Service.CSV;

import com.codecool.keepcash.Entity.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVServiceMilleniumImpl implements CSVService {

    public CSVServiceMilleniumImpl() {
    }

    @Override
    public List<Operation> parseCSVToOperations(MultipartFile file) throws ParseException, IOException {
        byte[] fileContent = file.getBytes();
        String parsedFile = new String(fileContent);
        List<String[]> operationArrays = parseCSVToListOfStringArrays(readCSVFile(parsedFile));

        return createListOfOperations(operationArrays);
    }

    @Override
    public List<String[]> parseCSVToListOfStringArrays(List<String[]> fileContent) {
        // first row in Millenium Bank .csv file is headers
        return fileContent.stream().skip(1).collect(Collectors.toList());
    }

    @Override
    public Operation createOperationFromSingleRow(String[] array) throws ParseException {
        int operationDateIndex = 1;
        int operationTypeIndex = 3;
        int operationNameIndex = 6;
        int operationExpanseIndex = 7;
        int operationIncomeIndex = 8;
        int operationCurrencyIndex = 10;

        Operation operation = new Operation();

        // date format defined in interface field
        operation.setDate(formatter.parse(array[operationDateIndex]));
        operation.setDescription(
                !array[operationNameIndex].isEmpty()
                        ? array[operationNameIndex] : array[operationTypeIndex]
        );
        operation.setValue(
                Double.parseDouble(!array[operationExpanseIndex].isEmpty()
                        ? array[operationExpanseIndex] : array[operationIncomeIndex])
        );

        return operation;
    }
}
