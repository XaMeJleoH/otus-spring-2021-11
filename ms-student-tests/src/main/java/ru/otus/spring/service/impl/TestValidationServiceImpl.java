package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.TestValidationService;

@Service
public class TestValidationServiceImpl implements TestValidationService {
    private final int passedNumber;

    public TestValidationServiceImpl(@Value("${test.passed.number.answer}") int passedNumber) {
        this.passedNumber = passedNumber;
    }

    @Override
    public boolean isPassed(int totalCorrectAnswer) {
        return totalCorrectAnswer >= passedNumber;
    }
}
