package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.TestExecutionService;

@RequiredArgsConstructor
public class TestSimpleExecutionServiceImpl implements TestExecutionService {
    private final QuestionService questionService;

    @Override
    public void test() throws QuestionsReadingException {
        Test test = questionService.getTest();
        test.getQuestionList().forEach(question -> {
            System.out.println(question.getQuestion());
            System.out.println(question.getTestAnswer());
        });
    }
}
