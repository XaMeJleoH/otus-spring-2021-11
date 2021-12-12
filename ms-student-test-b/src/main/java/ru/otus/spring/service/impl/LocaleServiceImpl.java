package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.LocaleProvider;
import ru.otus.spring.service.LocaleService;

@Service
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;
    private final LocaleProvider localeProvider;

    @Override
    public String getLocaleMessage(String message) {
        return messageSource.getMessage(message, null, localeProvider.getLocale());
    }

    @Override
    public String getLocaleMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, localeProvider.getLocale());
    }
}
