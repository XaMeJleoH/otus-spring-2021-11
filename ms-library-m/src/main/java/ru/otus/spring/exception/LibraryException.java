package ru.otus.spring.exception;

public class LibraryException extends Exception {

    public LibraryException(Throwable err) {
        super(err);
    }

    public LibraryException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public LibraryException(String errorMessage) {
        super(errorMessage);
    }

    public LibraryException() {
        super("Something wrong");
    }
}
