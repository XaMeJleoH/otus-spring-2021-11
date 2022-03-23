package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.jpa.Book;
import ru.otus.spring.model.jpa.Comment;
import ru.otus.spring.model.jpa.Genre;
import ru.otus.spring.model.mongo.Author;
import ru.otus.spring.repository.mongo.AuthorRepository;
import ru.otus.spring.repository.mongo.BookRepository;
import ru.otus.spring.repository.mongo.CommentRepository;
import ru.otus.spring.repository.mongo.GenreRepository;
import ru.otus.spring.service.MigrationService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MigrationJPAtoMongoServiceImpl implements MigrationService {
    private final AuthorRepository mongoAuthorRepository;
    private final ru.otus.spring.repository.jpa.AuthorRepository jpaAuthorRepository;
    private final GenreRepository mongoGenreRepository;
    private final ru.otus.spring.repository.jpa.GenreRepository jpaGenreRepository;
    private final BookRepository mongoBookRepository;
    private final ru.otus.spring.repository.jpa.BookRepository jpaBookRepository;
    private final CommentRepository mongoCommentRepository;
    private final ru.otus.spring.repository.jpa.CommentRepository jpaCommentRepository;

    @Override
    public void migrateAuthor() {
        List<ru.otus.spring.model.jpa.Author> authorList = jpaAuthorRepository.findAll();
        mongoAuthorRepository.saveAll(getMongoAuthorList(authorList));
    }

    private List<Author> getMongoAuthorList(List<ru.otus.spring.model.jpa.Author> authorList) {
        return authorList.stream().map(this::getAuthor).collect(Collectors.toUnmodifiableList());
    }

    private Author getAuthor(ru.otus.spring.model.jpa.Author author) {
        return new Author(author.getName());
    }

    @Override
    public void migrateGenre() {
        List<ru.otus.spring.model.jpa.Genre> genreList = jpaGenreRepository.findAll();
        mongoGenreRepository.saveAll(getMongoGenreList(genreList));
    }

    private List<ru.otus.spring.model.mongo.Genre> getMongoGenreList(List<Genre> genreList) {
        return genreList.stream().map(this::getGenre).collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Genre getGenre(Genre genre) {
        return new ru.otus.spring.model.mongo.Genre(genre.getName());
    }

    @Override
    public void migrateBook() {
        List<Book> bookList = jpaBookRepository.findAll();
        mongoBookRepository.saveAll(getMongoBookList(bookList));
    }

    private List<ru.otus.spring.model.mongo.Book> getMongoBookList(List<Book> bookList) {
        List<Author> authorList = mongoAuthorRepository.findAll();
        List<ru.otus.spring.model.mongo.Genre> genreList = mongoGenreRepository.findAll();
        return bookList.stream()
                .map(book -> getBook(book, authorList, genreList))
                .collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Book getBook(Book book, List<Author> authorList,
                                                    List<ru.otus.spring.model.mongo.Genre> genreList) {
        List<Author> mongoAuthorList = defineMongoAuthorList(book, authorList);
        List<ru.otus.spring.model.mongo.Genre> mongoGenreList = defineMongoGenreList(book, genreList);

        ru.otus.spring.model.mongo.Book mongoBook = new ru.otus.spring.model.mongo.Book();
        mongoBook.setName(book.getName());
        mongoBook.setAuthorList(mongoAuthorList);
        mongoBook.setGenreList(mongoGenreList);

        return mongoBook;
    }

    private List<ru.otus.spring.model.mongo.Genre> defineMongoGenreList(Book book, List<ru.otus.spring.model.mongo.Genre> genreList) {
        List<String> genreNameList = book.getGenreList().stream()
                .map(ru.otus.spring.model.jpa.Genre::getName)
                .collect(Collectors.toUnmodifiableList());
        return genreList.stream()
                .filter(genre -> genreNameList.contains(genre.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Author> defineMongoAuthorList(Book book, List<Author> authorList) {
        List<String> authorNameList = book.getAuthorList().stream()
                .map(ru.otus.spring.model.jpa.Author::getName)
                .collect(Collectors.toUnmodifiableList());
        return authorList.stream()
                .filter(author -> authorNameList.contains(author.getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void migrateComment() {
        List<Comment> commentList = jpaCommentRepository.findAll();
        mongoCommentRepository.saveAll(getMongoCommentList(commentList));
    }

    private List<ru.otus.spring.model.mongo.Comment> getMongoCommentList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::getMongoComment)
                .collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Comment getMongoComment(Comment comment) {
        Book book = comment.getBook();
        ru.otus.spring.model.mongo.Book mongoBook = mongoBookRepository.findByName(book.getName());
        return new ru.otus.spring.model.mongo.Comment(mongoBook, comment.getMessage());
    }

}
