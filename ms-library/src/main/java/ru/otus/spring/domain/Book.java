package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    private long id;
    private final String name;
    private final Author author;

    //private final Set<BookGenre> bookGenreSet;
}
