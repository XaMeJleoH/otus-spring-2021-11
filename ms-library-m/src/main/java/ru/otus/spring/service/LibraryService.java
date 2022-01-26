package ru.otus.spring.service;

import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;

public interface LibraryService {
    void showAllBook();

    boolean createAuthor(Author author);

    void showAllAuthor();

    boolean createGenre(Genre genre);

    void showAllGenre();

    boolean publicBook(String name, String authorId, String[] genres) throws LibraryException;

    boolean addComment(String bookId, String comment) throws LibraryException;

    void showAllCommentsBook(String bookId) throws LibraryException;

    boolean updateBookName(String bookId, String name) throws LibraryException;

    void deleteBook(String bookId) throws LibraryException;
}
