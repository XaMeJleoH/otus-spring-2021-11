package ru.otus.spring.component.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.component.LocaleMessageService;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;

@Component
@RequiredArgsConstructor
public class ConsoleLocaleMessageServiceImpl implements LocaleMessageService {
    private final IOService ioService;
    private final LocaleService localeService;

    @Override
    public void print(String message) {
        ioService.print(localeService.getLocaleMessage(message));
    }

    @Override
    public void print(String message, Object... args) {
        ioService.print(localeService.getLocaleMessage(message, args));
    }
}
