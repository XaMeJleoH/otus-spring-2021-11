package ru.otus.spring.repository.jpa.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.jpa.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestHelper.DONTZOVA;
import static ru.otus.spring.TestHelper.FANTASY;
import static ru.otus.spring.TestHelper.HARRY;
import static ru.otus.spring.TestHelper.ROY;
import static ru.otus.spring.TestHelper.STORY;
import static ru.otus.spring.TestHelper.createBook;

@DisplayName("Репозиторий на основе Jpa для работы библиотекой ")
@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void save() {
        Book book = createBook();
        book = repositoryJpa.save(book);
        List<Book> bookList = repositoryJpa.findAll();
        assertBookList(bookList);
    }


    private void assertBookList(List<Book> bookList) {
        assertEquals(1, bookList.size());
        assertTrue(bookList.stream().anyMatch(book1 -> book1.getName().equals(HARRY)));
        assertEquals(2, bookList.get(0).getAuthorList().size());
        assertTrue(bookList.get(0).getAuthorList().stream().anyMatch(author -> author.getName().equals(DONTZOVA)));
        assertTrue(bookList.get(0).getAuthorList().stream().anyMatch(author -> author.getName().equals(ROY)));
        assertEquals(2, bookList.get(0).getGenreList().size());
        assertTrue(bookList.get(0).getGenreList().stream().anyMatch(genre -> genre.getName().equals(FANTASY)));
        assertTrue(bookList.get(0).getGenreList().stream().anyMatch(genre -> genre.getName().equals(STORY)));
    }

    @Test
    void findById() {
        Book book = createBook();
        book = repositoryJpa.save(book);
        Optional<Book> bookOptional = repositoryJpa.findById(book.getId());
        assertTrue(bookOptional.isPresent());
        assertBook(bookOptional.get());
    }

    private void assertBook(Book book) {
        assertEquals(HARRY, book.getName());
        assertTrue(book.getAuthorList().stream().anyMatch(author -> author.getName().equals(DONTZOVA)));
        assertTrue(book.getAuthorList().stream().anyMatch(author -> author.getName().equals(ROY)));
        assertEquals(2, book.getGenreList().size());
        assertTrue(book.getGenreList().stream().anyMatch(genre -> genre.getName().equals(FANTASY)));
        assertTrue(book.getGenreList().stream().anyMatch(genre -> genre.getName().equals(STORY)));
    }


    @Test
    void findAll() {
        Book book = createBook();
        book = repositoryJpa.save(book);
        List<Book> bookList = repositoryJpa.findAll();
        assertBookList(bookList);
    }
}