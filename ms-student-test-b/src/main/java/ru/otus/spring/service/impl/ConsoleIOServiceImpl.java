package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.service.StreamConfigService;

import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class ConsoleIOServiceImpl implements IOService {
    private final LocaleService localeService;
    private final StreamConfigService streamConfigService;

    @Override
    public void print(String message) {
        streamConfigService.printStream().println(message);
    }

    @Override
    public void printWithLocale(String message, Object... args) {
        streamConfigService.printStream().println(localeService.getLocaleMessage(message, args));
    }

    public void printWithLocale(String message) {
        streamConfigService.printStream().println(localeService.getLocaleMessage(message));
    }

    @Override
    public void printFormat(String format, Object... args) {
        String message = new Formatter().format(format, args).toString();
        streamConfigService.printStream().println(message);
    }

    @Override
    public String get() {
        return streamConfigService.getScanner().nextLine();
    }

}
