package ru.otus.spring.service;

import java.util.Locale;

public interface LocaleService {
    String getLocaleMessage(String message, Locale locale);

    String getLocaleMessage(String message, Locale locale, Object... args);

    void setLocale(String locale);

    Locale defineLocale(String localeString);
}
