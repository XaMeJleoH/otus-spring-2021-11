package ru.otus.spring.repository.jpa.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@DisplayName("Репозиторий на основе Mongo для работы библиотекой ")
class BookRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("при удалении book не должен удалять автора")
    @Test
    void test() {
        val bookList = bookRepository.findAll();
        val book = bookList.get(0);
        val authorList = book.getAuthorList();
        val author = authorList.get(0);

        bookRepository.delete(book);

        assertTrue(authorRepository.findById(author.getId()).isPresent());
    }
}