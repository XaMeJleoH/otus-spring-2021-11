package ru.otus.spring.repository;

import ru.otus.spring.model.Book;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();
}
