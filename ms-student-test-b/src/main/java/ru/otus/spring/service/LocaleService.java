package ru.otus.spring.service;

public interface LocaleService {
    String getLocaleMessage(String message);

    String getLocaleMessage(String message, Object... args);
}
