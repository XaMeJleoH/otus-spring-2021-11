package ru.otus.spring.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentInputValidationServiceImplTest {

    @InjectMocks
    private StudentInputValidationServiceImpl service;

    @Test
    void checkAnswer() {
        assertTrue(service.checkAnswer("hi", "hI"));
        assertTrue(service.checkAnswer("green", "greEN"));
        assertFalse(service.checkAnswer("red", "rad"));
    }
}