package ru.otus.spring.service;

import ru.otus.spring.shell.event.publisher.TestEvent;

public interface TestRunnerService {
    void run(TestEvent testEvent);
}
