package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentInputValidationServiceImplTest {

    private StudentInputValidationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new StudentInputValidationServiceImpl();
    }

    @Test
    void checkAnswer() {
        assertTrue(service.checkAnswer("hi", "hI"));
        assertTrue(service.checkAnswer("green", "greEN"));
        assertFalse(service.checkAnswer("red", "rad"));
    }
}