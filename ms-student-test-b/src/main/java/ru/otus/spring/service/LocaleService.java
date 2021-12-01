package ru.otus.spring.service;

public interface LocaleService {
    String getLocaleMessage(String message, Object... args);
    void setLocale(String locale);
}
