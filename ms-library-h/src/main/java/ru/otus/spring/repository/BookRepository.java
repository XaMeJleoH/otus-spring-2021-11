package ru.otus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
