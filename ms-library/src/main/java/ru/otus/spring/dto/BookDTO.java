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
    private final String name;
    private final AuthorDTO author;
    private final List<GenreDTO> genreList;
}
