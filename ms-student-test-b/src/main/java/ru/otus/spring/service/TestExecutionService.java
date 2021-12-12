package ru.otus.spring.service;

import ru.otus.spring.exception.StudentTestException;

public interface TestExecutionService {
    void test() throws StudentTestException;
}
