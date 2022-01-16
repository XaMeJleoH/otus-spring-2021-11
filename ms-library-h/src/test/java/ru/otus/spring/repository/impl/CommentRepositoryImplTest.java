package ru.otus.spring.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestHelper.COMMENT;
import static ru.otus.spring.TestHelper.createBook;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями")
@DataJpaTest
class CommentRepositoryImplTest {

    @Autowired
    private CommentRepository repositoryJpa;

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    private Book book;

    @BeforeEach
    void setUp() {
        book = bookRepositoryJpa.save(createBook());
    }

    @Test
    void save() {
        Comment comment = saveComment();
        assertNotNull(comment);
        assertEquals(COMMENT, comment.getMessage());
    }

    private Comment saveComment() {
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setMessage(COMMENT);
        comment = repositoryJpa.save(comment);
        return comment;
    }

    @Test
    void findById() {
        Comment tempComment = saveComment();
        Optional<Comment> commentOptional = repositoryJpa.findById(tempComment.getId());
        assertTrue(commentOptional.isPresent());
        assertEquals(COMMENT, commentOptional.get().getMessage());
    }

    @Test
    void delete() {
        Comment tempComment = saveComment();
        Optional<Comment> commentOptional = repositoryJpa.findById(tempComment.getId());
        assertTrue(commentOptional.isPresent());
        repositoryJpa.delete(commentOptional.get());

        Optional<Comment> commentDeletedOptional = repositoryJpa.findById(tempComment.getId());
        assertTrue(commentDeletedOptional.isEmpty());

        System.out.println(bookRepositoryJpa.findById(book.getId()));
        assertTrue(bookRepositoryJpa.findById(book.getId()).isPresent());
    }
}