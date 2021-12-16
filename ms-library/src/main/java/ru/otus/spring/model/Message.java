package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String authorName;
    private String bookName;
    private List<String> genreNameList;

    public Message(List<String> genreNameList) {
        this.genreNameList = genreNameList;
    }
}
