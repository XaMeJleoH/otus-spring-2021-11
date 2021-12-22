package ru.otus.spring.component;

public interface LocaleMessageService {
    void print(String message);

    void print(String message, Object... args);
}
