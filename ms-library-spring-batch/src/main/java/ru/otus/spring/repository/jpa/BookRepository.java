package ru.otus.spring.repository.jpa;

import ru.otus.spring.model.jpa.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();
}
