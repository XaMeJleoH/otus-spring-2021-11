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

    boolean publicBook(String name, long authorId, long[] genres) throws LibraryException;

    boolean addComment(long bookId, String comment) throws LibraryException;
}
