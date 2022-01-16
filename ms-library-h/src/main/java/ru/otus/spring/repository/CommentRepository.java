package ru.otus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
