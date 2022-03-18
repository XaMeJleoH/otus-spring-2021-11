package ru.otus.spring.service;

import ru.otus.spring.model.mongo.Author;
import ru.otus.spring.model.mongo.Book;
import ru.otus.spring.model.mongo.Comment;
import ru.otus.spring.model.mongo.Genre;

public interface MigrationService {
    Author migrate(ru.otus.spring.model.jpa.Author author);

    Genre migrate(ru.otus.spring.model.jpa.Genre genre);

    Book migrate(ru.otus.spring.model.jpa.Book book);

    Comment migrate(ru.otus.spring.model.jpa.Comment comment);
}
