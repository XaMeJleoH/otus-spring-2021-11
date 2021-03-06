package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.*;
import ru.otus.spring.service.*;

@Service
@RequiredArgsConstructor
public class TestSimpleExecutionServiceImpl implements TestExecutionService {
    private static final String QUESTION_LAST_NAME = "What is your last name?";
    private static final String QUESTION_FIRST_NAME = "What is your name?";

    private final QuestionService questionService;
    private final IOService ioService;
    private final InputValidationService inputValidationService;
    private final TestValidationService testValidationService;

    @Override
    public void test() throws StudentTestException {
        Test test = null;
        try {
            test = questionService.getTest();
        } catch (QuestionsReadingException e) {
            throw new StudentTestException("Problem with read test question. Exception: " + e.getMessage());
        }
        User user = getUser();
        TestResult testResult = askQuestionAndCheckAnswer(test);
        testResult.setUser(user);
        testResult.setTest(test);

        showResultTest(testResult);
        checkPassTest(testResult);
    }

    private void checkPassTest(TestResult testResult) {
        if (testValidationService.isPassed(testResult.getCorrectAnswer())) {
            ioService.print("Congratulation! You passed test");
        } else {
            ioService.print("Sorry, you dont passed test");
        }
    }

    private void showResultTest(TestResult testResult) {
        ioService.printFormat("Testing result for Student with Last name is %s, and First Name is %s",
                testResult.getUser().getLastName(), testResult.getUser().getFirstName());
        ioService.printFormat("Total was %s question. Student answered correct %s times",
                testResult.getTest().getTotalQuestion(), testResult.getCorrectAnswer());
    }

    private TestResult askQuestionAndCheckAnswer(Test test) {
        TestResult testResult = new TestResult();
        test.getQuestionList().forEach(question -> {
            ioService.print(question.getQuestion());
            if (inputValidationService.checkAnswer(question.getAnswer(), ioService.get())) {
                testResult.applyCorrectAnswer();
            }
        });
        return testResult;
    }

    private User getUser() {
        User user = new User();
        ioService.print(QUESTION_LAST_NAME);
        user.setLastName(ioService.get());
        ioService.print(QUESTION_FIRST_NAME);
        user.setFirstName(ioService.get());
        return user;
    }
}
