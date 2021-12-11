package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.model.User;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.TestRunnerService;
import ru.otus.spring.shell.event.publisher.TestEvent;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestExecutionService testExecutionService;
    private final IOService ioService;

    @Override
    @EventListener
    public void run(TestEvent testEvent) {
        try {
            User user = testEvent.getUser();
            ioService.printWithLocale("test.hello", user.getLocale(), user);
            testExecutionService.test(user);
        } catch (StudentTestException e) {
            ioService.printFormat("Something happened. Error: ", e.getMessage());
        }
    }
}
