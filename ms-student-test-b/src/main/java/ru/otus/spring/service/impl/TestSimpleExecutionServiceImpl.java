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
        User user = getUser();
        try {
            test = questionService.getTest(user.getLocale());
        } catch (QuestionsReadingException e) {
            throw new StudentTestException(localeService.getLocaleMessage("error.question.total", user.getLocale(), e.getMessage()));
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

    private User getUser() {
        User user = new User();
        user.setLocale(localeService.defineLocale(ioService.get()));
        ioService.printWithLocale("test.locale.set", user.getLocale());
        ioService.print(localeService.getLocaleMessage("test.question.last.name", user.getLocale()));
        user.setLastName(ioService.get());
        ioService.print(localeService.getLocaleMessage("test.question.name", user.getLocale()));
        user.setFirstName(ioService.get());
        return user;
    }
}
