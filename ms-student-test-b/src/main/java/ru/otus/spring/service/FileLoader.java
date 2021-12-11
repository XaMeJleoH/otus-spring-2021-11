package ru.otus.spring.service;

import ru.otus.spring.model.QuestionsReadingException;

import java.io.InputStream;
import java.util.Locale;

public interface FileLoader {
    InputStream loadFile(String classPath, Locale locale) throws QuestionsReadingException;
}
