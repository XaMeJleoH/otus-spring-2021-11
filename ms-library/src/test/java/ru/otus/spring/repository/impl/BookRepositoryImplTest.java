package ru.otus.spring.repository.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.dao.impl.AuthorDaoJdbc;
import ru.otus.spring.dao.impl.BookDaoJdbc;
import ru.otus.spring.dao.impl.BookGenreDaoJdbc;
import ru.otus.spring.dao.impl.GenreDaoJdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestUtil.*;

@DisplayName("Repository для работы с книгами и его сущностями")
@JdbcTest
@Import({BookRepositoryImpl.class, AuthorDaoJdbc.class, BookDaoJdbc.class, BookGenreDaoJdbc.class, GenreDaoJdbc.class})
class BookRepositoryImplTest {
    @Autowired
    private BookRepositoryImpl bookRepository;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(bookRepository.getAllBook()));
        bookRepository.insertBook(createBook(DANTZOVA, SADOVNIK, FANTASY, STORY));
        assertEquals(1, bookRepository.getAllBook().size());
    }


    @Test
    void getBookById() {
        val book = bookRepository.getBookById(1);
        assertEquals(DANTZOVA, book.getAuthor().getName());
        assertEquals(SADOVNIK, book.getName());
        assertEquals(2, book.getGenreList().size());
        assertTrue(book.getGenreList().stream().anyMatch(genreDTO -> genreDTO.getName().equals(FANTASY)));
        assertTrue(book.getGenreList().stream().anyMatch(genreDTO -> genreDTO.getName().equals(STORY)));
    }

    @Test
    void getAllBook() {
        val bookDTOList = bookRepository.getAllBook();
        assertEquals(1, bookDTOList.size());
        val book = bookDTOList.get(0);
        assertEquals(DANTZOVA, book.getAuthor().getName());
        assertEquals(SADOVNIK, book.getName());
        assertEquals(2, book.getGenreList().size());
        assertTrue(book.getGenreList().stream().anyMatch(genreDTO -> genreDTO.getName().equals(FANTASY)));
        assertTrue(book.getGenreList().stream().anyMatch(genreDTO -> genreDTO.getName().equals(STORY)));
    }

    @Test
    void insertBook() {
        bookRepository.insertBook(createBook("Rouling", "Harry potter", FANTASY));
        val bookDTOList = bookRepository.getAllBook();
        assertEquals(2, bookDTOList.size());
    }

    @Test
    void deleteBook() {
        val bookDTOList = bookRepository.getAllBook();
        assertEquals(1, bookDTOList.size());
        val book = bookDTOList.get(0);
        bookRepository.deleteBook(book);
        assertTrue(CollectionUtils.isEmpty(bookRepository.getAllBook()));
    }
}