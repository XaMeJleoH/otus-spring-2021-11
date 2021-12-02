package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.service.TestExecutionService;
import ru.otus.spring.service.TestRunnerService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {
    private final TestExecutionService testExecutionService;
    private final IOService ioService;
    private final LocaleService localeService;

    @Override
    public void run() {
        try {
            ioService.printWithLocale("test.hello", Locale.getDefault());
            testExecutionService.test();
        } catch (StudentTestException e) {
            ioService.printWithLocale("error.total", Locale.getDefault(), e.getMessage());
        }
    }
}
