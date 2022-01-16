package ru.otus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
