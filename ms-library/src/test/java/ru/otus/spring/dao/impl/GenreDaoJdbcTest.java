package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestUtil.FANTASY;
import static ru.otus.spring.TestUtil.STORY;

@DisplayName("GenreDao для работы с жанрами")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(genreDaoJdbc.getAll()));
        genreDaoJdbc.insert(new Genre(STORY));
        assertEquals(1, genreDaoJdbc.getAll().size());
    }

    @Test
    void insert() {
        genreDaoJdbc.insert(new Genre(FANTASY));
        assertEquals(2, genreDaoJdbc.getAll().size());
    }

    @Test
    void getById() {
        long genreId = genreDaoJdbc.insert(new Genre(FANTASY));
        assertEquals(2, genreDaoJdbc.getAll().size());

        Genre genre = genreDaoJdbc.getById(genreId);
        assertEquals(FANTASY, genre.getName());
    }

    @Test
    void getByName() {
        long genreId = genreDaoJdbc.insert(new Genre(FANTASY));
        assertEquals(2, genreDaoJdbc.getAll().size());

        Genre genre = genreDaoJdbc.getByName(FANTASY);
        assertEquals(genreId, genre.getId());
    }

    @Test
    void getAll() {
        genreDaoJdbc.insert(new Genre(FANTASY));
        assertEquals(2, genreDaoJdbc.getAll().size());
        List<Genre> genreList = genreDaoJdbc.getAll();
        assertTrue(genreList.stream().anyMatch(genre -> genre.getName().equals(FANTASY)));
        assertTrue(genreList.stream().anyMatch(genre -> genre.getName().equals(STORY)));
    }

    @Test
    void deleteById() {
        long genreId = genreDaoJdbc.insert(new Genre(FANTASY));
        assertEquals(2, genreDaoJdbc.getAll().size());
        genreDaoJdbc.deleteById(genreId);
        assertEquals(1, genreDaoJdbc.getAll().size());
    }
}