package ru.otus.spring.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.dao.impl.AuthorDaoJdbc;
import ru.otus.spring.dao.impl.BookDaoJdbc;
import ru.otus.spring.dao.impl.BookGenreDaoJdbc;
import ru.otus.spring.dao.impl.GenreDaoJdbc;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.impl.BookRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.otus.spring.TestUtil.*;

@DisplayName("Repository для работы с книгами и его сущностями")
@JdbcTest
@Import({LibraryServiceImpl.class, BookRepositoryImpl.class, AuthorDaoJdbc.class, BookDaoJdbc.class,
        BookGenreDaoJdbc.class, GenreDaoJdbc.class})
class LibraryServiceImplTest {

    public static final String VASYA = "Vasya";
    public static final String PATTERN = "Pattern";
    public static final String HOME = "home";
    public static final String BIOGRAPHY = "biography";
    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        assertTrue(CollectionUtils.isEmpty(bookRepository.getAllBook()));
        bookRepository.insertBook(createBook(DANTZOVA, SADOVNIK, FANTASY, STORY));
        assertEquals(1, bookRepository.getAllBook().size());
    }

    @Test
    void publicBook() {
        LibraryBook libraryBook = createLibraryBook();
        assertTrue(libraryService.publicBook(libraryBook));
        val bookList = bookRepository.getAllBook();
        assertEquals(2, bookList.size());
        assertTrue(bookList.stream().anyMatch(bookDTO -> bookDTO.getName().equals(PATTERN)));
        assertTrue(bookList.stream().anyMatch(bookDTO -> bookDTO.getAuthor().getName().equals(VASYA)));

        val bookDTO = bookList.stream().filter(bookDTO1 -> bookDTO1.getName().equals(PATTERN)).findAny().orElseThrow();
        assertEquals(2, bookDTO.getGenreList().size());
        assertTrue(bookDTO.getGenreList().stream().anyMatch(bookDTO1 -> bookDTO1.getName().equals(HOME)));
        assertTrue(bookDTO.getGenreList().stream().anyMatch(bookDTO1 -> bookDTO1.getName().equals(BIOGRAPHY)));
    }

    private LibraryBook createLibraryBook() {
        return LibraryBook.builder()
                .author(createAuthor())
                .name(PATTERN)
                .genreList(createGenreList(HOME, BIOGRAPHY))
                .build();
    }


    private List<Genre> createGenreList(String... args) {
        List<Genre> genreList = new ArrayList<>();
        Stream.of(args).forEach(arg -> genreList.add(createGenre(arg)));
        return genreList;
    }

    private Genre createGenre(String genreName) {
        return Genre.builder()
                .name(genreName)
                .build();
    }

    private Author createAuthor() {
        return Author.builder()
                .name(VASYA)
                .build();
    }
}