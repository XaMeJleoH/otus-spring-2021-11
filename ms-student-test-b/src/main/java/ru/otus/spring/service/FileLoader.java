package ru.otus.spring.service;

import ru.otus.spring.model.QuestionsReadingException;

import java.io.InputStream;

public interface FileLoader {
    InputStream loadFile(String classPath) throws QuestionsReadingException;
}
