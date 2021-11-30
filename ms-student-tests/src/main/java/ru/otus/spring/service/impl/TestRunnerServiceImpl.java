package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.TestRunnerService;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestExecutionService testExecutionService;
    private final IOService IOService;

    @Override
    public void run() {
        try {
            testExecutionService.test();
        } catch (StudentTestException e) {
            IOService.print("Something happened. Error: " + e.getMessage());
        }
    }
}
