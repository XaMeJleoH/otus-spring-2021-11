package ru.otus.spring.service;

import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.model.Test;

public interface QuestionService {
    Test getTest() throws StudentTestException;
}
