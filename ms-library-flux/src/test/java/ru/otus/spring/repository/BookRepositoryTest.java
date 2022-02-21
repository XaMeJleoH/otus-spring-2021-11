package ru.otus.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.spring.model.Book;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class BookRepositoryTest {
    private static final String ID_PUSHKIN = "62129bc1d0bf945247c59a2f";
    private static final String ID_LERMONTOV = "62129bc1d0bf945247c59a30";
    private static final String ID_TOLSTOY = "62129bc1d0bf945247c59a31";
    public static final String PUSHKIN = "Pushkin";
    public static final String LERMONTOV = "Lermontov";
    public static final String TOLSTOY = "Tolstoy";

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findById() {
        Mono<Book> bookMonoTemp = repository.save(new Book(PUSHKIN));
        Mono<Book> bookMono = repository.findById(bookMonoTemp.map(Book::getId));

        StepVerifier
                .create(bookMono)
                .assertNext(person -> assertNotNull(person.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    void save() {
        Mono<Book> bookMono = repository.save(new Book(PUSHKIN));

        StepVerifier
                .create(bookMono)
                .assertNext(person -> assertNotNull(person.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    void deleteById() {
        Mono<Book> bookMonoPushkin = repository.save(new Book(PUSHKIN));
        Mono<Book> bookMonoLermontov = repository.save(new Book(LERMONTOV));

        StepVerifier
                .create(bookMonoPushkin)
                .assertNext(person -> assertNotNull(person.getId()))
                .expectComplete()
                .verify();

        StepVerifier
                .create(bookMonoLermontov)
                .assertNext(person -> assertNotNull(person.getId()))
                .expectComplete()
                .verify();

        Mono<Void> bookMonoDeleted = repository.deleteById(ID_PUSHKIN);

        StepVerifier
                .create(bookMonoDeleted)
                .expectNextCount(0)
                .verifyComplete();
    }
}