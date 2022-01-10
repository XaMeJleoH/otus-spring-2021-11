package ru.otus.spring.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;

import java.util.Collections;

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
        Author author = new Author(0, "Dontzova");
        Genre genre = new Genre(0L, "s");
        Comment comment = new Comment(0L, "fsdfsfd");
        Book book = new Book(0L, "HARRY", author, Collections.singletonList(genre), Collections.singletonList(comment));
        repositoryJpa.save(book);
    }
}