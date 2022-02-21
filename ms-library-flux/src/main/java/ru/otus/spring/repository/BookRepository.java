package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findById(String bookId);

    Mono<Book> save(Mono<Book> book);

    Mono<Void> deleteById(Mono<String> id);

}
