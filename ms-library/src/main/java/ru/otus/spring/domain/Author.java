package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private long id;
    private String name;
    //private Set<Book> bookSet;

    public Author(long id) {
        this.id = id;
    }
}
