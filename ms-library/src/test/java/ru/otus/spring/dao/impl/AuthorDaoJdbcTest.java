package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.domain.Author;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AuthorDaoJdbc для работы с авторами")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(authorDaoJdbc.getAll()));
        authorDaoJdbc.insert(new Author("Vasya"));
        assertEquals(1, authorDaoJdbc.getAll().size());
    }

    @Test
    void insert() {
        authorDaoJdbc.insert(new Author("Dontzova"));
        assertEquals(2, authorDaoJdbc.getAll().size());
    }

    @Test
    void getById() {
        long authorId = authorDaoJdbc.insert(new Author("Dontzova"));
        assertEquals(2, authorDaoJdbc.getAll().size());
        assertNotNull(authorDaoJdbc.getById(authorId));
    }

    @Test
    void getAll() {
        authorDaoJdbc.insert(new Author("Dontzova"));
        assertEquals(2, authorDaoJdbc.getAll().size());
    }

    @Test
    void deleteById() {
        long authorId = authorDaoJdbc.insert(new Author("Dontzova"));
        assertEquals(2, authorDaoJdbc.getAll().size());
        authorDaoJdbc.deleteById(authorId);
        assertEquals(1, authorDaoJdbc.getAll().size());
    }
}