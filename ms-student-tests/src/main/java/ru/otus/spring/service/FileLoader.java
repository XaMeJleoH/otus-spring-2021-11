package ru.otus.spring.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.InputStream;

public interface FileLoader {
    InputStream loadFile(String classPath);
}
