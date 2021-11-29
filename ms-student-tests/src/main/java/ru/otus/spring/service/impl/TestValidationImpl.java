package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.service.TestValidation;

@RequiredArgsConstructor
public class TestValidationImpl implements TestValidation {
    private final int passedNumber;

    @Override
    public boolean isPassed(int totalCorrectAnswer) {
        return totalCorrectAnswer >= passedNumber;
    }
}
