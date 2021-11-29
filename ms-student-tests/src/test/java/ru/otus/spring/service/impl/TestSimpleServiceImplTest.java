package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TestSimpleServiceImplTest {

    @Mock
    private QuestionsFromCsvServiceImpl questionsFromCsvService;

    @InjectMocks
    private TestSimpleServiceImpl testSimpleService;

    private ru.otus.spring.model.Test buildTest() {
        final var test = new ru.otus.spring.model.Test();
        Map<String, String> map = new HashMap<>();
        map.put("Question", "Answer");
        map.put("Question 2", "Answer 2");
        test.setQuestionTestAnswerMap(map);
        return test;
    }

    @Test
    @DisplayName("Успешное тестирование")
    void test1() {
        when(questionsFromCsvService.getFile()).thenReturn(buildTest());
        boolean isSuccess = testSimpleService.test();
        assertTrue(isSuccess);
    }

    @Test
    @DisplayName("Негативное тестирование")
    void testNegative() {
        when(questionsFromCsvService.getFile()).thenReturn(null);
        boolean isSuccess = testSimpleService.test();
        assertFalse(isSuccess);
    }
}