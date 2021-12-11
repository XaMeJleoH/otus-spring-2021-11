package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.configuration.StreamConfig;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;

import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleIOServiceImpl implements IOService {
    private final LocaleService localeService;
    private final StreamConfig streamConfig = new StreamConfig();
    private final Scanner scanner = new Scanner(streamConfig.inputStream());

    @Override
    public void print(String message) {
        streamConfig.printStream().println(message);
    }

    @Override
    public void printWithLocale(String message, Locale locale) {
        streamConfig.printStream().println(localeService.getLocaleMessage(message, locale));
    }

    @Override
    public void printWithLocale(String message, Locale locale, Object... args) {
        streamConfig.printStream().println(localeService.getLocaleMessage(message, locale, args));
    }

    @Override
    public void printFormat(String format, Object... args) {
        String message = new Formatter().format(format, args).toString();
        streamConfig.printStream().println(message);
    }

    @Override
    public String get() {
        return scanner.nextLine();
    }

}
