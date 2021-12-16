package ru.otus.spring.dao;

import ru.otus.spring.domain.BookGenre;

import java.util.List;

public interface BookGenreDao {

    void insert(BookGenre bookGenre);

    List<BookGenre> getByBookId(long id);

    List<BookGenre> getByGenreId(long id);

    List<BookGenre> getAll();

    void deleteByGenreId(long id);

    void deleteByBookId(long id);
}
