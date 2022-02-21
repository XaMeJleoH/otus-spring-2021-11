package ru.otus.spring.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.rest.dto.BookDTO;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookControllerTest {

    private static final String ID_PUSHKIN = "62129bc1d0bf945247c59a2f";
    private static final String ID_LERMONTOV = "62129bc1d0bf945247c59a30";
    private static final String ID_TOLSTOY = "62129bc1d0bf945247c59a31";
    public static final String PUSHKIN = "Pushkin";
    public static final String LERMONTOV = "Lermontov";
    public static final String TOLSTOY = "Tolstoy";

    @MockBean
    private BookRepository repository;

    @Autowired
    private BookController bookController;

    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient
                .bindToController(bookController)
                .build();
        Mono<Book> bookMono = getMonoBook();
        when(repository.save(any(Mono.class))).thenReturn(bookMono);
        when(repository.findById(ID_PUSHKIN)).thenReturn(bookMono);
        when(repository.findAll()).thenReturn(bookMono.flatMapMany(Flux::just));
    }

    private Mono<Book> getMonoBook() {
        return Mono.just(new Book(PUSHKIN));
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
        client.put()
                .uri("/book/")
                .body(getMonoBookDTO(), BookDTO.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void removeBook() {
        client.delete()
                .uri("/book/" + ID_PUSHKIN)
                .exchange()
                .expectStatus()
                .isOk();
    }
}