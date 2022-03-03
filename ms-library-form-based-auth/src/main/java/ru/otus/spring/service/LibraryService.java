package ru.otus.spring.service;

import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Genre;

import java.util.List;

public interface LibraryService {
    List<Book> findAllBook();

    Book findBook(String id) throws LibraryException;

    void saveBook(Book book);

    boolean updateBookName(String bookId, String name) throws LibraryException;

    void deleteBook(String bookId) throws LibraryException;
}
