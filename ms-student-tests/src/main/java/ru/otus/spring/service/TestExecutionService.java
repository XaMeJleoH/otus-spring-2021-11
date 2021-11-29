package ru.otus.spring.service;

import ru.otus.spring.model.StudentTestException;

public interface TestExecutionService {
    void test() throws StudentTestException;
}
