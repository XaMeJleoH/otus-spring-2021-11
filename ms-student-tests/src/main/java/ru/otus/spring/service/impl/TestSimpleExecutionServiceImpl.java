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
        TestResult testResult = new TestResult();
        Test test = null;
        try {
            test = questionService.getTest();
        } catch (QuestionsReadingException e) {
            throw new StudentTestException("Problem with read test question. Exception: " + e.getMessage());
        }
        testResult.setTest(test);
        User user = getUser();

        readAndAskQuestion(testResult, test);
        showResultTest(testResult, test, user);

        checkPassTest(testResult);
    }

    private void checkPassTest(TestResult testResult) {
        if (testValidationService.isPassed(testResult.getCorrectAnswer())) {
            ioService.print("Congratulation! You passed test");
        } else {
            ioService.print("Sorry, you dont passed test");
        }
    }

    private void showResultTest(TestResult testResult, Test test, User user) {
        ioService.print(String.format("Testing result for Student with Last name is %s, and First Name is %s",
                user.getLastName(), user.getFirstName()));
        ioService.print(String.format("Total was %s question. Student answered correct %s times",
                test.getTotalQuestion(), testResult.getCorrectAnswer()));
    }

    private void readAndAskQuestion(TestResult testResult, Test test) {
        test.getQuestionList().forEach(question -> {
            ioService.print(question.getQuestion());
            if (inputValidationService.checkAnswer(question.getAnswer(), ioService.get())) {
                int correctAnswers = testResult.getCorrectAnswer();
                testResult.setCorrectAnswer(++correctAnswers);
            }
        });
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
