package ru.otus.spring;

import ru.otus.spring.dto.AuthorDTO;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.dto.GenreDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestUtil {
    public static final String DANTZOVA = "Dantzova";
    public static final String SADOVNIK = "Sadovnik";
    public static final String FANTASY = "fantasy";
    public static final String STORY = "story";

    public TestUtil() {
    }

    public static BookDTO createBook(String authorName, String bookName, String... args) {
        return BookDTO.builder()
                .author(AuthorDTO.builder()
                        .name(authorName)
                        .build())
                .name(bookName)
                .genreList(createGenreList(args))
                .build();
    }

    private static List<GenreDTO> createGenreList(String[] args) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        Stream.of(args).forEach(arg -> genreDTOList.add(createGenre(arg)));
        return genreDTOList;
    }

    private static GenreDTO createGenre(String genreName) {
        return GenreDTO.builder()
                .name(genreName)
                .build();
    }
}
