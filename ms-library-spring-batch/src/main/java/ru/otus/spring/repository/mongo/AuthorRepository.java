package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.mongo.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
