package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.component.LocaleMessageService;
import ru.otus.spring.dao.QuestionService;
import ru.otus.spring.exception.QuestionsReadingException;
import ru.otus.spring.exception.StudentTestException;
import ru.otus.spring.model.Test;
import ru.otus.spring.model.TestResult;
import ru.otus.spring.model.User;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.InputValidationService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.TestValidationService;

@Service
@RequiredArgsConstructor
public class TestSimpleExecutionServiceImpl implements TestExecutionService {
    private final QuestionService questionService;
    private final LocaleMessageService localeMessageService;
    private final IOService ioService;
    private final InputValidationService inputValidationService;
    private final TestValidationService testValidationService;

    @Override
    public void test(User user) throws StudentTestException {
        Test test = null;
        try {
            test = questionService.getTest();
        } catch (QuestionsReadingException e) {
            throw new StudentTestException("Problem with read test question. Exception: " + e.getMessage());
        }
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
            localeMessageService.print("test.passed");
        } else {
            localeMessageService.print("test.not.passed");
        }
    }

    private void showResultTest(TestResult testResult) {
        localeMessageService.print("test.result.name", testResult.getUser().getLastName(), testResult.getUser().getFirstName());
        localeMessageService.print("test.result", testResult.getTest().getTotalQuestion(), testResult.getCorrectAnswer());
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
