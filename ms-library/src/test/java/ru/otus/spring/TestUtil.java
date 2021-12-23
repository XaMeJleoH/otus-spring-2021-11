package ru.otus.spring;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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

    public static Book createBook(String authorName, String bookName, String... args) {
        return Book.builder()
                .author(Author.builder()
                        .name(authorName)
                        .build())
                .name(bookName)
                .genreList(createGenreList(args))
                .build();
    }

    private static List<Genre> createGenreList(String[] args) {
        List<Genre> genreDTOList = new ArrayList<>();
        Stream.of(args).forEach(arg -> genreDTOList.add(createGenre(arg)));
        return genreDTOList;
    }

    private static Genre createGenre(String genreName) {
        return Genre.builder()
                .name(genreName)
                .build();
    }
}