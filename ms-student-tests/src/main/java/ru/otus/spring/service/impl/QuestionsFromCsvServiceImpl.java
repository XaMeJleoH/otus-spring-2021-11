package ru.otus.spring.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.FileLoader;
import ru.otus.spring.service.QuestionService;

import java.io.*;
import java.util.*;

@Setter
@RequiredArgsConstructor
public class QuestionsFromCsvServiceImpl implements QuestionService {
    private String csvFile;
    private static final String COMMA_DELIMITER = ";";

    private final FileLoader fileLoader;

    @Override
    public Test getFile() {
        InputStream inputStream = fileLoader.loadFile(csvFile);
        if (inputStream == null) {
            return null;
        }
        Map<String, String> testQuestionTestAnswerMap = getQuestionMap(inputStream);
        return new Test(testQuestionTestAnswerMap);
    }

    private Map<String, String> getQuestionMap(InputStream inputStream) {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));) {
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

}
