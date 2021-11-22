package ru.otus.spring.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.QuestionService;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Setter
public class QuestionsFromCsvServiceImpl implements QuestionService {
    private String csvFile;
    private static final String COMMA_DELIMITER = ";";

    @Override
    public Test getFile() {
        Resource resource = new ClassPathResource(csvFile);
        File file = getFile(resource);
        if (file == null) {
            return null;
        }

        Map<String, String> testQuestionTestAnswerMap = getQuestionMap(file);
        return new Test(testQuestionTestAnswerMap);
    }

    private Map<String, String> getQuestionMap(File file) {
        try (CSVReader csvReader = new CSVReader(new FileReader(file));) {
            return getMapFromReader(csvReader);
        } catch (CsvValidationException | IOException e) {
            System.out.println("Can not read the file.");
        }
        return null;
    }

    private Map<String, String> getMapFromReader(CSVReader csvReader)
            throws IOException, CsvValidationException {
        Map<String, String> testQuestionTestAnswerMap = new HashMap<>();
        String[] row = null;
        while ((row = csvReader.readNext()) != null) {
            if (row.length == 1) {
                String[] values = row[0].split(COMMA_DELIMITER);
                if (values.length == 2) {
                    testQuestionTestAnswerMap.put(values[0], values[1]);
                }
            }
        }
        return testQuestionTestAnswerMap;
    }

    private File getFile(Resource resource) {
        try {
            return resource.getFile();
        } catch (IOException e) {
            System.out.println("File is not found. File destination: " + csvFile);
            return null;
        }
    }
}
