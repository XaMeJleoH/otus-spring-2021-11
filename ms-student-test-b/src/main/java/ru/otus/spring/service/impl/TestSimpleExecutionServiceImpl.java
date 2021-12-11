package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionService;
import ru.otus.spring.model.*;
import ru.otus.spring.service.*;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TestSimpleExecutionServiceImpl implements TestExecutionService {
    private final QuestionService questionService;
    private final IOService ioService;
    private final InputValidationService inputValidationService;
    private final TestValidationService testValidationService;
    private final LocaleService localeService;

    @Override
    public void test(User user) throws StudentTestException {
        Test test = null;
        try {
            test = questionService.getTest(user.getLocale());
        } catch (QuestionsReadingException e) {
            throw new StudentTestException("Problem with read test question. Exception: " + e.getMessage());
        }
        TestResult testResult = getTestResult(test, user);

        showResultTest(testResult, user.getLocale());
        checkPassTest(testResult, user.getLocale());
    }

    private TestResult getTestResult(Test test, User user) {
        TestResult testResult = askQuestionAndCheckAnswer(test);
        testResult.setUser(user);
        testResult.setTest(test);
        return testResult;
    }

    private void checkPassTest(TestResult testResult, Locale locale) {
        if (testValidationService.isPassed(testResult.getCorrectAnswer())) {
            ioService.print(localeService.getLocaleMessage("test.passed", locale));
        } else {
            ioService.print(localeService.getLocaleMessage("test.not.passed", locale));
        }
    }

    private void showResultTest(TestResult testResult, Locale locale) {
        ioService.printFormat(localeService.getLocaleMessage("test.result.name", locale,
                testResult.getUser().getLastName(), testResult.getUser().getFirstName()));
        ioService.printFormat(localeService.getLocaleMessage("test.result", locale,
                testResult.getTest().getTotalQuestion(), testResult.getCorrectAnswer()));
    }

    private TestResult askQuestionAndCheckAnswer(Test test) {
        TestResult testResult = new TestResult();
        test.getQuestionList().forEach(question -> {
            ioService.print(question.getQuestion());
            if (inputValidationService.checkAnswer(question.getAnswer(), ioService.get())) {
                testResult.increementCorrectAnswer();
            }
        });
        return testResult;
    }

}
