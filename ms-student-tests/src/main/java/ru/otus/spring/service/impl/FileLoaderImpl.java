package ru.otus.spring.service.impl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.service.FileLoader;

import java.io.File;
import java.io.IOException;

public class FileLoaderImpl implements FileLoader {

    @Override
    public File loadFile(String classPath) {
        Resource resource = new ClassPathResource(classPath);
        try {
            return resource.getFile();
        } catch (IOException e) {
            System.out.println("File is not found. ClassPath= " + classPath);
        }
        return null;
    }
}
