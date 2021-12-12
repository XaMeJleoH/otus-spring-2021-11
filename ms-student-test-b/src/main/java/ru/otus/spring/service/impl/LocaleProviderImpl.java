package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocaleProvider;

import java.util.Locale;

@Service
public class LocaleProviderImpl implements LocaleProvider {
    private Locale locale;

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String localeString) {
        this.locale = Locale.forLanguageTag(localeString);
    }
}
