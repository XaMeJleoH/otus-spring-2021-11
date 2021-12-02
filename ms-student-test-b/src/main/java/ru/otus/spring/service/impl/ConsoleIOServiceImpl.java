package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleIOServiceImpl implements IOService {
    private final LocaleService localeService;
    private final PrintStream printStream;
    private final InputStream inputStream;
    private final Scanner scannerIn = new Scanner(inputStream);

    @Override
    public void print(String message) {
        printStream.println(message);
    }

    @Override
    public void printWithLocale(String message) {
        printStream.println(localeService.getLocaleMessage(message));
    }

    @Override
    public void printFormat(String format, Object... args) {
        String message = new Formatter().format(format, args).toString();
        printStream.println(message);
    }

    @Override
    public String get() {
        return scannerIn.nextLine();
    }

}
