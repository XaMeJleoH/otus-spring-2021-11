package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBook {
    private Author author;
    private String name;
    private List<Genre> genreList;
}
