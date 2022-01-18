package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> getAllByBook(Book book);
}
