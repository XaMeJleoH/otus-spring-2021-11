package ru.otus.spring.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.otus.spring.model.StudentTestException;
import ru.otus.spring.service.FileLoader;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FileLoaderImpl implements FileLoader {

    @Override
    public InputStream loadFile(String classPath) throws StudentTestException {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new StudentTestException("File is not found. ClassPath= " + classPath);
        }
    }
}
