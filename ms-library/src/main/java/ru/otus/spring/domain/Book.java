package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class Book {
    private final long id;
    private final String name;

    private final Set<BookGenre> bookGenreSet;
}
