package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.exception.QuestionsReadingException;
import ru.otus.spring.service.FileLoader;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class FileLoaderImpl implements FileLoader {

    @Override
    public InputStream loadFile(String classPath) throws QuestionsReadingException {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new QuestionsReadingException("File is not found. ClassPath= " + classPath);
        }
    }
}
