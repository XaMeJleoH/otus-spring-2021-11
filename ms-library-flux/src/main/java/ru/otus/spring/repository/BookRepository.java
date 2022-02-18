package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.spring.model.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findById(String bookId);

    Mono<Book> save(Mono<Book> book);

}
