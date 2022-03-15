package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String name;

    private List<Author> authorList;

    private List<Genre> genreList;

    public Book(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public Book(String name, List<Author> authorList, List<Genre> genreList) {
        this.name = name;
        this.authorList = authorList;
        this.genreList = genreList;
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
