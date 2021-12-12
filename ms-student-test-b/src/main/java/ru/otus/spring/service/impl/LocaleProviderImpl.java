package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocaleProvider;

import java.util.Locale;

@Service
public class LocaleProviderImpl implements LocaleProvider {

    @Override
    public void setLocale(String localeString) {
        Locale.setDefault(Locale.forLanguageTag(localeString));
    }
}
