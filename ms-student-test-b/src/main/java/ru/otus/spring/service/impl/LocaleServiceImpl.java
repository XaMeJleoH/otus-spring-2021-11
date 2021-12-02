package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocaleService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;

    @Override
    public String getLocaleMessage(String message, Locale locale) {
        return messageSource.getMessage(message, null, locale);
    }

    @Override
    public String getLocaleMessage(String message, Locale locale, Object... args) {
        return messageSource.getMessage(message, args, locale);
    }

    @Override
    public void setLocale(String locale) {
        Locale.setDefault(new Locale(locale));
    }

    @Override
    public Locale defineLocale(String localeString) {
        return new Locale(localeString);
    }
}
