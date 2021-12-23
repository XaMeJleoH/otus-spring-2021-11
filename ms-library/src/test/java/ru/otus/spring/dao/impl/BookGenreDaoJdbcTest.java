package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestUtil.*;

@DisplayName("BookGenreDaoJdbcTest для работы с вспомогательной таблицей для кинг и жанров")
@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class, BookGenreDaoJdbc.class, GenreDaoJdbc.class})
class BookGenreDaoJdbcTest {

    @Autowired
    private BookGenreDaoJdbc bookGenreDaoJdbc;

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(bookDaoJdbc.getAll()));
        bookDaoJdbc.insert(createBook(DANTZOVA, SADOVNIK, FANTASY, STORY));
        assertEquals(1, bookDaoJdbc.getAll().size());
    }

    @Test
    void insert() {
        bookDaoJdbc.insert(createBook("Vasya", "Harry potter", FANTASY));
        assertEquals(2, bookDaoJdbc.getAll().size());
    }

    @Test
    void getByGenreId() {
        long bookId = bookDaoJdbc.insert(createBook("Vasya", "Harry potter", FANTASY));
        Book book = bookDaoJdbc.getById(bookId);
        List<BookGenre> bookGenreList = bookGenreDaoJdbc.getByGenreId(book.getGenreList().get(0).getId());
        assertEquals(2, bookGenreList.size());
        assertTrue(bookGenreList.stream().anyMatch(bookGenre -> bookGenre.getGenre().getName().equals(FANTASY)));
    }

    @Test
    void getByBookId() {
        long bookId = bookDaoJdbc.insert(createBook("Vasya", "Harry potter", FANTASY, STORY));
        List<BookGenre> bookGenreList = bookGenreDaoJdbc.getByBookId(bookId);
        assertEquals(2, bookGenreList.size());
        assertTrue(bookGenreList.stream().anyMatch(bookGenre -> bookGenre.getGenre().getName().equals(FANTASY)));
        assertTrue(bookGenreList.stream().anyMatch(bookGenre -> bookGenre.getGenre().getName().equals(STORY)));
    }

    @Test
    void getAll() {
        List<BookGenre> bookGenreList = bookGenreDaoJdbc.getAll();
        assertEquals(2, bookGenreList.size());
    }
}