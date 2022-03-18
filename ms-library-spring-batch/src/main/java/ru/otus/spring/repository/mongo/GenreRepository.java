package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.mongo.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
