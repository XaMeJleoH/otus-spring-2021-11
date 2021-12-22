package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.component.LocaleMessageService;
import ru.otus.spring.exception.StudentTestException;
import ru.otus.spring.model.User;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.TestRunnerService;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestExecutionService testExecutionService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void run(User user) {
        try {
            testExecutionService.test(user);
        } catch (StudentTestException e) {
            localeMessageService.print("test.error", e.getMessage());
        }
    }
}
