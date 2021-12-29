package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestUtil.DANTZOVA;
import static ru.otus.spring.TestUtil.FANTASY;
import static ru.otus.spring.TestUtil.SADOVNIK;
import static ru.otus.spring.TestUtil.STORY;
import static ru.otus.spring.TestUtil.createBook;

@DisplayName("BookDaoJdbc для работы с книгами и её сущностями")
@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(bookDaoJdbc.getAll()));
        bookDaoJdbc.insert(createBook(DANTZOVA, SADOVNIK, FANTASY, STORY));
        assertEquals(1, bookDaoJdbc.getAll().size());
    }

    @Test
    void insert() {
        bookDaoJdbc.insert(createBook("VASYA", "Harry potter", FANTASY));
        assertEquals(2, bookDaoJdbc.getAll().size());
    }

    @Test
    void getById() {
        long bookId = bookDaoJdbc.insert(createBook("VASYA", "Harry potter", FANTASY));
        assertEquals(2, bookDaoJdbc.getAll().size());
        assertNotNull(bookDaoJdbc.getById(bookId));
    }

    @Test
    void getAll() {
        bookDaoJdbc.insert(createBook("VASYA", "Harry potter", FANTASY));
        assertEquals(2, bookDaoJdbc.getAll().size());
    }

    @Test
    void deleteById() {
        long bookId = bookDaoJdbc.insert(createBook("VASYA", "Harry potter", FANTASY));
        assertEquals(2, bookDaoJdbc.getAll().size());
        bookDaoJdbc.deleteById(bookId);
        assertEquals(1, bookDaoJdbc.getAll().size());
    }
}