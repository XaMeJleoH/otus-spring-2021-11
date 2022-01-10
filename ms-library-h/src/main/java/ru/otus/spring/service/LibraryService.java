package ru.otus.spring.service;


import ru.otus.spring.model.Book;

public interface LibraryService {
    boolean publicBook(Book libraryBook);

    void showAllBook();
}
