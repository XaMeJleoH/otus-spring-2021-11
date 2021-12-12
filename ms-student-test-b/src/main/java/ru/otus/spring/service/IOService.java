package ru.otus.spring.service;

import java.util.Locale;

public interface IOService {
    void print(String message);

    void printWithLocale(String message);

    void printWithLocale(String message, Object... args);

    void printFormat(String format, Object... args);

    String get();
}
