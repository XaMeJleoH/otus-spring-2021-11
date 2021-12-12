package ru.otus.spring.exception;

public class StudentTestException extends Exception {

    public StudentTestException(Throwable err) {
        super(err);
    }

    public StudentTestException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public StudentTestException(String errorMessage) {
        super(errorMessage);
    }
}
