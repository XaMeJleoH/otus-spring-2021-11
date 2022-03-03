package ru.otus.spring.repository;

import ru.otus.spring.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(long authorId);

    List<Author> findAll();
}
