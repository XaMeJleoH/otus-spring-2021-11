package ru.otus.spring.service;

import ru.otus.spring.model.LibraryBook;

public interface LibraryService {
    boolean publicBook(LibraryBook libraryBook);

    void showAllBook();
}
