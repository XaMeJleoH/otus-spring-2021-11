package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.mongo.Author;
import ru.otus.spring.model.mongo.Book;
import ru.otus.spring.model.mongo.Comment;
import ru.otus.spring.model.mongo.Genre;
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
    public Author migrate(ru.otus.spring.model.jpa.Author author) {
        List<ru.otus.spring.model.jpa.Author> authorList = jpaAuthorRepository.findAll();
        mongoAuthorRepository.saveAll(getMongoAuthorList(authorList));
    }

    private List<Author> getMongoAuthorList(List<ru.otus.spring.model.jpa.Author> authorList) {
        return authorList.stream().map(this::getAuthor).collect(Collectors.toUnmodifiableList());
    }

    private Author getAuthor(ru.otus.spring.model.jpa.Author author) {
        return new Author(author.getId(), author.getName());
    }

    @Override
    public Genre migrate(ru.otus.spring.model.jpa.Genre genre) {
        return null;
    }

    @Override
    public Book migrate(ru.otus.spring.model.jpa.Book book) {
        return null;
    }

    @Override
    public Comment migrate(ru.otus.spring.model.jpa.Comment comment) {
        return null;
    }
}
