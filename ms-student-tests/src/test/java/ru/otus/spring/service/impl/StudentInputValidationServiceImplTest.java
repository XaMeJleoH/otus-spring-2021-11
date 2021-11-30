package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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