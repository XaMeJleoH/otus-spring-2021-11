package ru.otus.spring.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.FileLoader;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionsFromCsvServiceImpl implements QuestionService {
    private final LocaleService localeService;
    private static final String COMMA_DELIMITER = ";";
    private final FileLoader fileLoader;
    private final String csvFilePath;

    public QuestionsFromCsvServiceImpl(LocaleService localeService, FileLoader fileLoader, @Value("${test.file.csv}") String csvFilePath) {
        this.localeService = localeService;
        this.fileLoader = fileLoader;
        this.csvFilePath = csvFilePath;
    }

    @Override
    public Test getTest() throws QuestionsReadingException {
        InputStream inputStream = fileLoader.loadFile(csvFilePath);
        if (inputStream == null) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.file.can.not.read"));
        }
        return getTestFromInputStream(inputStream);
    }

    private Test getTestFromInputStream(InputStream inputStream) throws QuestionsReadingException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));) {
            Map<String, String> testQuestionTestAnswerMap = getMapFromReader(csvReader);
            return buildTest(testQuestionTestAnswerMap);
        } catch (CsvValidationException | IOException | QuestionsReadingException e) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.file.can.not.read.as.csv"));
        }
    }

    private Test buildTest(Map<String, String> testQuestionTestAnswerMap) throws QuestionsReadingException {
        if (testQuestionTestAnswerMap.size() == 0) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.can.not.find.question"));
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
                    throw new CsvValidationException(localeService.getLocaleMessage("error.question.size.column.not.correct"));
                }
            } else {
                throw new CsvValidationException(localeService.getLocaleMessage("error.question.size.row.not.correct"));
            }
        }
        return testQuestionTestAnswerMap;
    }

}
