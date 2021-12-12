package ru.otus.spring.service;

import java.util.Locale;

public interface LocaleProvider {
    Locale getLocale();
    void setLocale(String localeString);
}
