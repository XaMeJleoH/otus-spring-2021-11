package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.*;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TestSimpleExecutionServiceImpl implements TestExecutionService {
    private static final String QUESTION_LAST_NAME = "What is your last name?";
    private static final String QUESTION_FIRST_NAME = "What is your name?";

    private final QuestionService questionService;
    private final CustomPrintService printService;
    private final InputValidationService inputValidationService;
    private final TestValidation testValidation;

    @Override
    public void test() throws StudentTestException {
        Test test = questionService.getTest();
        Scanner scanner = new Scanner(System.in);
        printService.print(QUESTION_LAST_NAME);
        test.setLastName(scanner.nextLine());
        printService.print(QUESTION_FIRST_NAME);
        test.setFirstName(scanner.nextLine());

        test.getQuestionList().forEach(question -> {
            printService.print(question.getQuestion());
            if (inputValidationService.checkAnswer(question.getAnswer(), scanner.nextLine())) {
                int correctAnswers = test.getCorrectAnswer();
                test.setCorrectAnswer(++correctAnswers);
            }
        });
        printService.print(String.format("Testing result for Student with Last name is %s, and First Name is %s",
                test.getLastName(), test.getFirstName()));
        printService.print(String.format("Total was %s question. Student answered correct %s times",
                test.getTotalQuestion(), test.getCorrectAnswer()));

        if (testValidation.isPassed(test.getCorrectAnswer())) {
            printService.print("Congratulation! You passed test");
        } else {
            printService.print("Sorry, you dont passed test");
        }
    }
}
