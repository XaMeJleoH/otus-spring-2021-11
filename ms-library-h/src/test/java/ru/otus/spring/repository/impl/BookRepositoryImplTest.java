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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Репозиторий на основе Jpa для работы библиотекой ")
@DataJpaTest
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    public static final String HARRY = "HARRY";
    public static final String DONTZOVA = "Dontzova";
    public static final String STORY = "Story";
    public static final String FANTASY = "Fantasy";
    public static final String COMMENT1 = "Amazing book";
    public static final String COMMENT = "Tanya Grotter is better when this...";
    public static final String COMMENT2 = "PERFECTO!";
    @Autowired
    private BookRepositoryImpl repositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void save() {
        Book book = saveBook();
        List<Book> bookList = repositoryJpa.findAll();
        assertBookList(bookList);

        book.getCommentList().add(new Comment(COMMENT2));
        assertTrue(bookList.get(0).getCommentList().stream().anyMatch(comment -> comment.getMessage().equals(COMMENT2)));
    }

    private void assertBookList(List<Book> bookList) {
        assertEquals(1, bookList.size());
        assertTrue(bookList.stream().anyMatch(book1 -> book1.getName().equals(HARRY)));
        assertTrue(bookList.stream().anyMatch(book1 -> book1.getAuthor().getName().equals(DONTZOVA)));
        assertTrue(bookList.get(0).getGenreList().stream().anyMatch(genre -> genre.getName().equals(FANTASY)));
        assertTrue(bookList.get(0).getCommentList().stream().anyMatch(comment -> comment.getMessage().equals(COMMENT1)));
        assertTrue(bookList.get(0).getCommentList().stream().anyMatch(comment -> comment.getMessage().equals(COMMENT)));
        assertTrue(bookList.get(0).getCommentList().stream().noneMatch(comment -> comment.getMessage().equals(COMMENT2)));
    }

    private Book saveBook() {
        Author author = createAuthor(DONTZOVA);
        List<Genre> genreList = createGenreList();
        List<Comment> commentList = createCommentList();
        Book book = new Book(HARRY, author, genreList, commentList);

        book = repositoryJpa.save(book);
        return book;
    }

    private List<Comment> createCommentList() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(createComment(COMMENT));
        commentList.add(createComment(COMMENT1));
        return commentList;
    }

    private List<Genre> createGenreList() {
        List<Genre> genreList = new ArrayList<>();
        genreList.add(createGenre(STORY));
        genreList.add(createGenre(FANTASY));
        return genreList;
    }

    private Comment createComment(String comment) {
        return new Comment(comment);
    }

    private Genre createGenre(String genre) {
        return new Genre(genre);
    }

    private Author createAuthor(String author) {
        return new Author(author);
    }

    @Test
    void findById() {
        Book savedBook = saveBook();
        Optional<Book> bookOptional = repositoryJpa.findById(savedBook.getId());
        assertTrue(bookOptional.isPresent());
        assertBook(bookOptional.get());
    }

    private void assertBook(Book book) {
        assertEquals(HARRY, book.getName());
        assertEquals(DONTZOVA, book.getAuthor().getName());
        assertTrue(book.getGenreList().stream().anyMatch(genre -> genre.getName().equals(FANTASY)));
        assertTrue(book.getCommentList().stream().anyMatch(comment -> comment.getMessage().equals(COMMENT1)));
        assertTrue(book.getCommentList().stream().anyMatch(comment -> comment.getMessage().equals(COMMENT)));
        assertTrue(book.getCommentList().stream().noneMatch(comment -> comment.getMessage().equals(COMMENT2)));
    }

    @Test
    void findAll() {
        Book book = saveBook();
        List<Book> bookList = repositoryJpa.findAll();
        assertBookList(bookList);
    }
}