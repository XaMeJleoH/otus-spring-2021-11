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
    public String getLocaleMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, Locale.getDefault());
    }

    @Override
    public void setLocale(String locale) {
        Locale.setDefault(new Locale(locale));
    }
}
