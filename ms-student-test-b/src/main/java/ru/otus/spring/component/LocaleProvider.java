package ru.otus.spring.component;

import java.util.Locale;

public interface LocaleProvider {
    void setLocale(String localeString);

    Locale getLocale();
}
