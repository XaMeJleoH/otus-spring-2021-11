package ru.otus.spring.service;

public interface InputValidationService {
    boolean checkAnswer(String correctAnswer, String clientAnswer);
}
