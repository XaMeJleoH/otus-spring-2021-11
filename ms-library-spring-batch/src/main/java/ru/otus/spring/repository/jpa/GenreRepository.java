package ru.otus.spring.repository.jpa;

import ru.otus.spring.model.jpa.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);

    Optional<Genre> findById(long genreId);

    List<Genre> findAll();
}
