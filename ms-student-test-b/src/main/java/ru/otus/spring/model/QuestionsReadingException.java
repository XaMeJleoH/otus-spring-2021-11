package ru.otus.spring.model;

public class QuestionsReadingException extends Exception {

    public QuestionsReadingException(Throwable err) {
        super(err);
    }

    public QuestionsReadingException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public QuestionsReadingException(String errorMessage) {
        super(errorMessage);
    }
}
