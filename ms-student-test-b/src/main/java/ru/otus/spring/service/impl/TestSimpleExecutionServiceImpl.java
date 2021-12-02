package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
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
    public void test() throws StudentTestException {
        Test test = null;
        try {
            test = questionService.getTest();
        } catch (QuestionsReadingException e) {
            throw new StudentTestException(localeService.getLocaleMessage("error.question.total", e.getMessage()));
        }
        User user = getUser();
        TestResult testResult = getTestResult(test, user);

        showResultTest(testResult);
        checkPassTest(testResult);
    }

    private TestResult getTestResult(Test test, User user) {
        TestResult testResult = askQuestionAndCheckAnswer(test);
        testResult.setUser(user);
        testResult.setTest(test);
        return testResult;
    }

    private void checkPassTest(TestResult testResult) {
        if (testValidationService.isPassed(testResult.getCorrectAnswer())) {
            ioService.print(localeService.getLocaleMessage("test.passed"));
        } else {
            ioService.print(localeService.getLocaleMessage("test.not.passed"));
        }
    }

    private void showResultTest(TestResult testResult) {
        ioService.printFormat(localeService.getLocaleMessage("test.result.name",
                testResult.getUser().getLastName(), testResult.getUser().getFirstName()));
        ioService.printFormat(localeService.getLocaleMessage("test.result",
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

    private User getUser() {
        User user = new User();
        ioService.print(localeService.getLocaleMessage("test.question.last.name"));
        user.setLastName(ioService.get());
        ioService.print(localeService.getLocaleMessage("test.question.name"));
        user.setFirstName(ioService.get());
        return user;
    }
}
