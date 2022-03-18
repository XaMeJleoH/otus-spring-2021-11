package ru.otus.spring.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.mongo.Book;
import ru.otus.spring.model.mongo.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> getAllByBook(Book book);

    void deleteAllByBook(Book book);
}
