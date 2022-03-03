package ru.otus.spring.service;

public interface IOService {
    void print(String message);

    void printFormat(String format, Object... args);
}
