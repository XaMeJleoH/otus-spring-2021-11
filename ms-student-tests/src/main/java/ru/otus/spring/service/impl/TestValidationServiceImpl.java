package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.service.TestValidationService;

@RequiredArgsConstructor
public class TestValidationServiceImpl implements TestValidationService {
    private final int passedNumber;

    @Override
    public boolean isPassed(int totalCorrectAnswer) {
        return totalCorrectAnswer >= passedNumber;
    }
}
