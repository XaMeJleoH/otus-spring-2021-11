package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String name;
    private long authorId;

    public Book(String name, long authorId) {
        this.name = name;
        this.authorId = authorId;
    }
}
