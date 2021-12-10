package ru.otus.spring.dao;

import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.Test;

import java.util.Locale;

public interface QuestionService {
    Test getTest(Locale locale) throws QuestionsReadingException;
}
