package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.model.Test;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.TestService;

@RequiredArgsConstructor
public class TestSimpleServiceImpl implements TestService {
    private final QuestionService questionService;

    @Override
    public boolean test() {
        Test test = questionService.getFile();
        if (test == null) {
            System.out.println("Question was not asked.");
            return false;
        }
        if (test.getQuestionTestAnswerMap() == null || test.getQuestionTestAnswerMap().size() == 0) {
            System.out.println("Can not find the questions");
            return false;
        }
        test.getQuestionTestAnswerMap().forEach((testQuestion, testAnswer) -> {
            System.out.println("Question: " + testQuestion);
            System.out.println("Answer: " + testAnswer);
        });
        return true;
    }
}
