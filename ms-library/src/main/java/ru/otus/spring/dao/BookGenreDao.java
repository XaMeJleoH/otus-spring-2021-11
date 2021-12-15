package ru.otus.spring.dao;

import ru.otus.spring.domain.BookGenre;

import java.util.Set;

public interface BookGenreDao {

    void insert(BookGenre bookGenre);

    BookGenre getById(long id);

    Set<BookGenre> getAll();

    void deleteById(long id);
}
