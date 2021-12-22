package ru.otus.spring.dao;

import ru.otus.spring.exception.QuestionsReadingException;
import ru.otus.spring.model.Test;

public interface QuestionService {
    Test getTest() throws QuestionsReadingException;
}
