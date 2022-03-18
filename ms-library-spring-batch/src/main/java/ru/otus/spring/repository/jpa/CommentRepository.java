package ru.otus.spring.repository.jpa;

import ru.otus.spring.model.jpa.Comment;

import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void delete(Comment comment);
}
