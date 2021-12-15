package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.Set;

public interface GenreDao {

    void insert(Genre genre);

    Genre getById(long id);

    Set<Genre> getAll();

    void deleteById(long id);
}
