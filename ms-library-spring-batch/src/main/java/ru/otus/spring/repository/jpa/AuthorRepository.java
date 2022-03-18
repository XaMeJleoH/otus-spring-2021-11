package ru.otus.spring.repository.jpa;

import ru.otus.spring.model.jpa.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(long authorId);

    List<Author> findAll();
}
