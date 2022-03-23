package ru.otus.spring.service.migrate.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.jpa.Book;
import ru.otus.spring.model.mongo.Author;
import ru.otus.spring.model.mongo.Genre;
import ru.otus.spring.repository.mongo.AuthorRepository;
import ru.otus.spring.repository.mongo.BookRepository;
import ru.otus.spring.repository.mongo.GenreRepository;
import ru.otus.spring.service.migrate.MigrateBookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MigrateBookServiceImpl implements MigrateBookService {
    private final BookRepository mongoBookRepository;
    private final ru.otus.spring.repository.jpa.BookRepository jpaBookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void migrate() {
        List<Book> bookList = jpaBookRepository.findAll();
        mongoBookRepository.saveAll(getMongoBookList(bookList));
    }

    private List<ru.otus.spring.model.mongo.Book> getMongoBookList(List<Book> bookList) {
        List<Author> authorList = authorRepository.findAll();
        List<ru.otus.spring.model.mongo.Genre> genreList = genreRepository.findAll();
        return bookList.stream()
                .map(book -> getBook(book, authorList, genreList))
                .collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Book getBook(Book book, List<Author> authorList,
                                                    List<Genre> genreList) {
        List<Author> mongoAuthorList = defineMongoAuthorList(book, authorList);
        List<Genre> mongoGenreList = defineMongoGenreList(book, genreList);

        ru.otus.spring.model.mongo.Book mongoBook = new ru.otus.spring.model.mongo.Book();
        mongoBook.setName(book.getName());
        mongoBook.setAuthorList(mongoAuthorList);
        mongoBook.setGenreList(mongoGenreList);

        return mongoBook;
    }

    private List<ru.otus.spring.model.mongo.Genre> defineMongoGenreList(Book book, List<Genre> genreList) {
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
}
