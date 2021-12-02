package ru.otus.spring.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.CSVLocale;
import ru.otus.spring.model.Question;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.CSVLoader;
import ru.otus.spring.service.FileLoader;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionsFromCsvServiceImpl implements QuestionService {
    private final LocaleService localeService;
    private static final String COMMA_DELIMITER = ";";
    private final FileLoader fileLoader;
    private final CSVLoader csvLoader;

    @Override
    public Test getTest(Locale locale) throws QuestionsReadingException {
        CSVLocale csvLocale = getCsvLocale(locale);
        InputStream inputStream = fileLoader.loadFile(csvLoader.defineCSVClasspath(csvLocale), locale);
        if (inputStream == null) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.file.can.not.read", locale));
        }
        return getTestFromInputStream(inputStream, locale);
    }

    private CSVLocale getCsvLocale(Locale locale) {
        Set<CSVLocale> csvLocaleList = csvLoader.load();
        return csvLocaleList.stream().filter(object -> object.getLocale().equals(locale)).findAny().orElseThrow();
    }

    private Test getTestFromInputStream(InputStream inputStream, Locale locale) throws QuestionsReadingException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            Map<String, String> testQuestionTestAnswerMap = getMapFromReader(csvReader, locale);
            return buildTest(testQuestionTestAnswerMap, locale);
        } catch (CsvValidationException | IOException | QuestionsReadingException e) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.file.can.not.read.as.csv", locale));
        }
    }

    private Test buildTest(Map<String, String> testQuestionTestAnswerMap, Locale locale) throws QuestionsReadingException {
        if (testQuestionTestAnswerMap.size() == 0) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.can.not.find.question", locale));
        }
        Test test = new Test(new ArrayList<>());
        testQuestionTestAnswerMap.forEach((testQuestion, testAnswer) -> {
            List<Question> questionList = test.getQuestionList();
            questionList.add(new Question(testQuestion, testAnswer));
        });
        test.setTotalQuestion(test.getQuestionList().size());
        return test;
    }

    private Map<String, String> getMapFromReader(CSVReader csvReader, Locale locale)
            throws IOException, CsvValidationException {
        Map<String, String> testQuestionTestAnswerMap = new HashMap<>();
        String[] row = null;
        while ((row = csvReader.readNext()) != null) {
            if (row.length == 1) {
                String[] values = row[0].split(COMMA_DELIMITER);
                if (values.length == 2) {
                    testQuestionTestAnswerMap.put(values[0], values[1]);
                } else {
                    throw new CsvValidationException(
                            localeService.getLocaleMessage("error.question.size.column.not.correct", locale));
                }
            } else {
                throw new CsvValidationException(localeService.getLocaleMessage("error.question.size.row.not.correct", locale));
            }
        }
        return testQuestionTestAnswerMap;
    }

}
