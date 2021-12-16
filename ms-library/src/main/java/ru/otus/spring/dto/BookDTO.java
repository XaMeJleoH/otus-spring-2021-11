package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookDTO {
    private long id;
    private String name;
    private AuthorDTO author;
    private List<GenreDTO> genreList;

    public BookDTO(String name, AuthorDTO author, List<GenreDTO> genreList) {
        this.name = name;
        this.author = author;
        this.genreList = genreList;
    }
}
