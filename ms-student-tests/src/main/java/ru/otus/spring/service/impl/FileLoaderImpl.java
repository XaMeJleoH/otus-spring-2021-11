package ru.otus.spring.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import ru.otus.spring.service.FileLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileLoaderImpl implements FileLoader {

    @Override
    public InputStream loadFile(String classPath) {
        ClassPathResource resource = new ClassPathResource(classPath);
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            System.out.println("File is not found. ClassPath= " + classPath);
        }
        return null;
    }
}
