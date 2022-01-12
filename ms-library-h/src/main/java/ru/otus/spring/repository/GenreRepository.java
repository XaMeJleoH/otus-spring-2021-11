package ru.otus.spring.repository;

import ru.otus.spring.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);

    Optional<Genre> findById(long genreId);

    List<Genre> findAll();
}
