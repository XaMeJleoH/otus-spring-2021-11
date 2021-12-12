package ru.otus.spring.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.configuration.CSVLoaderConfig;
import ru.otus.spring.dao.QuestionService;
import ru.otus.spring.exception.QuestionsReadingException;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.CSVLoader;
import ru.otus.spring.service.FileLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionsFromCsvServiceImpl implements QuestionService {
    private static final String COMMA_DELIMITER = ";";
    private final FileLoader fileLoader;
    private final CSVLoader csvLoader;
    private final CSVLoaderConfig csvLoaderConfig;

    @Override
    public Test getTest() throws QuestionsReadingException {
        InputStream inputStream = fileLoader.loadFile(getCsvPath());
        if (inputStream == null) {
            throw new QuestionsReadingException("Can not read the file");
        }
        return getTestFromInputStream(inputStream);
    }

    private String getCsvPath() {
        List<CSVLoaderConfig.CSVFileConfig> csvFileConfigList = csvLoaderConfig.getCsvFileConfigList();
        return csvFileConfigList.stream().filter(csvFileConfig ->
                Locale.forLanguageTag(csvFileConfig.getLocale()).equals(Locale.getDefault()))
                .findAny()
                .map(CSVLoaderConfig.CSVFileConfig::getCsvFile)
                .orElseThrow();
    }

    private Test getTestFromInputStream(InputStream inputStream) throws QuestionsReadingException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            Map<String, String> testQuestionTestAnswerMap = getMapFromReader(csvReader);
            return buildTest(testQuestionTestAnswerMap);
        } catch (CsvValidationException | IOException | QuestionsReadingException e) {
            throw new QuestionsReadingException("Can not read file as CSV");
        }
    }

    private Test buildTest(Map<String, String> testQuestionTestAnswerMap) throws QuestionsReadingException {
        if (testQuestionTestAnswerMap.size() == 0) {
            throw new QuestionsReadingException("Can not find the questions");
        }
        Test test = new Test(new ArrayList<>());
        testQuestionTestAnswerMap.forEach((testQuestion, testAnswer) -> {
            List<Question> questionList = test.getQuestionList();
            questionList.add(new Question(testQuestion, testAnswer));
        });
        test.setTotalQuestion(test.getQuestionList().size());
        return test;
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
                } else {
                    throw new CsvValidationException("The size of column is not correct");
                }
            } else {
                throw new CsvValidationException("The size of row not correct");
            }
        }
        return testQuestionTestAnswerMap;
    }

}
