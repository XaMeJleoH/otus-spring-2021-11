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
    private final MessageSource messageSource;
    private final LocaleService localeService;

    @Override
    public void run() {
        try {
            ioService.printWithLocale("test.hello");
            localeService.setLocale(ioService.get());
            ioService.printWithLocale("test.locale.set");
            testExecutionService.test();
        } catch (StudentTestException e) {
            ioService.printWithLocale("error.total", e.getMessage());
        }
    }
}
