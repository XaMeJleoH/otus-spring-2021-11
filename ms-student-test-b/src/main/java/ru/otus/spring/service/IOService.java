package ru.otus.spring.service;

public interface IOService {
    void print(String message);

    void printWithLocale(String message);

    void printWithLocale(String message, Object... args);

    void printFormat(String format, Object... args);

    String get();
}
