package ru.otus.spring.service.migrate.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.jpa.Genre;
import ru.otus.spring.repository.mongo.GenreRepository;
import ru.otus.spring.service.migrate.MigrateGenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MigrateGenreServiceImpl implements MigrateGenreService {
    private final GenreRepository mongoGenreRepository;
    private final ru.otus.spring.repository.jpa.GenreRepository jpaGenreRepository;

    @Override
    public void migrate() {
        List<Genre> genreList = jpaGenreRepository.findAll();
        mongoGenreRepository.saveAll(getMongoGenreList(genreList));
    }

    private List<ru.otus.spring.model.mongo.Genre> getMongoGenreList(List<Genre> genreList) {
        return genreList.stream().map(this::getGenre).collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Genre getGenre(Genre genre) {
        return new ru.otus.spring.model.mongo.Genre(genre.getName());
    }

}
