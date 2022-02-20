package ru.otus.spring.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.dto.BookDTO;

import java.util.Arrays;

@SpringBootTest
class BookControllerTest {

    private static final String ID_PUSHKIN = "62129bc1d0bf945247c59a2f";
    private static final String ID_LERMONTOV = "62129bc1d0bf945247c59a30";
    private static final String ID_TOLSTOY = "62129bc1d0bf945247c59a31";
    public static final String PUSHKIN = "Pushkin";
    public static final String LERMONTOV = "Lermontov";
    public static final String TOLSTOY = "Tolstoy";
    @Autowired
    private BookController bookController;
    @Autowired
    private BookRepository repository;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToController(bookController)
                .build();

        repository.saveAll(Arrays.asList(
                new Book(ID_PUSHKIN, PUSHKIN),
                new Book(ID_LERMONTOV, LERMONTOV),
                new Book(ID_TOLSTOY, TOLSTOY)
        ));
    }

    @Test
    void getAllBooks() {
        client.get()
                .uri("/book/")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void getBook() {
        client.get()
                .uri("/book/" + ID_PUSHKIN)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void saveBook() {
        client.post()
                .uri("/book/")
                .body(getMonoBookDTO(), BookDTO.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private Mono<BookDTO> getMonoBookDTO() {
        return Mono.just(new BookDTO(PUSHKIN));
    }

    @Test
    void updateBook() {
    }

    @Test
    void removeBook() {
    }
}