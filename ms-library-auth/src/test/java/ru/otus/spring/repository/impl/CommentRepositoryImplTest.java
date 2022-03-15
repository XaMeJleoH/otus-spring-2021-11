package ru.otus.spring.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@DisplayName("Репозиторий на основе Mongo для работы с комментариями")
class CommentRepositoryImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("при удалении comment не должен удалять book")
    @Test
    void test() {
        val bookList = bookRepository.findAll();
        val book = bookList.get(0);
        val commentList = commentRepository.getAllByBook(book);
        val comment = commentList.get(0);

        commentRepository.delete(comment);

        assertTrue(bookRepository.findById(book.getId()).isPresent());

        val expectedCommentSize = commentList.size() - 1;
        assertEquals(expectedCommentSize, commentRepository.getAllByBook(book).size());
    }
}