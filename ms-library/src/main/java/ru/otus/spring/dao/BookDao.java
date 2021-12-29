package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    long insert(Book book);

    void insertGenreLink(long bookId, List<String> genreList);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

}
