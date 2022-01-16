package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
