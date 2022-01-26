package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private Book book;

    private String message;

    public Comment(String message) {
        this.message = message;
    }

    public Comment(Book book, String message) {
        this.book = book;
        this.message = message;
    }
}
