package ru.otus.spring.service;

import ru.otus.spring.exception.QuestionsReadingException;

import java.io.InputStream;

public interface FileLoader {
    InputStream loadFile(String classPath) throws QuestionsReadingException;
}
