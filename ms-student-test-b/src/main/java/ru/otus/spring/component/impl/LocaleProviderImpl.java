package ru.otus.spring.component.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.component.LocaleProvider;

import java.util.Locale;

@Component
public class LocaleProviderImpl implements LocaleProvider {

    private Locale locale = Locale.getDefault();

    @Override
    public void setLocale(String localeString) {
        this.locale = Locale.forLanguageTag(localeString);
    }

    public Locale getLocale() {
        return this.locale;
    }
}
