package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.FileLoader;
import ru.otus.spring.model.QuestionsReadingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class FileLoaderImpl implements FileLoader {

    @Override
    public InputStream loadFile(String classPath, Locale locale) throws QuestionsReadingException {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new QuestionsReadingException("File is not found. ClassPath= " + classPath);
        }
    }
}
