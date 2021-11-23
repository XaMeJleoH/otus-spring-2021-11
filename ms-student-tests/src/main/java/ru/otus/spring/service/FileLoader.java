package ru.otus.spring.service;

import org.springframework.core.io.Resource;

import java.io.File;

public interface FileLoader {
    File loadFile(String classPath);
}
