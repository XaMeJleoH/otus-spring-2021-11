package ru.otus.spring.service;

import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.model.User;

public interface TestExecutionService {
    void test(User user) throws StudentTestException;
}
