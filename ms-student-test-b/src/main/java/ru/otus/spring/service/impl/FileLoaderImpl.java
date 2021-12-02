package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.service.FileLoader;
import ru.otus.spring.service.LocaleService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class FileLoaderImpl implements FileLoader {
    private final LocaleService localeService;

    @Override
    public InputStream loadFile(String classPath, Locale locale) throws QuestionsReadingException {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new QuestionsReadingException(localeService.getLocaleMessage("error.file.not.found", locale, classPath));
        }
    }
}
