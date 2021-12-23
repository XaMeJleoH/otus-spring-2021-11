package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String name;
    private Author author;
    private List<Genre> genreList;

    public Book(long id) {
        this.id = id;
    }

    public Book(String name, Author author, List<Genre> genreList) {
        this.name = name;
        this.author = author;
        this.genreList = genreList;
    }
}
