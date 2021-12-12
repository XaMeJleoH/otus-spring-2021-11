package ru.otus.spring.dao;

import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.Test;

public interface QuestionService {
    Test getTest() throws QuestionsReadingException;
}
