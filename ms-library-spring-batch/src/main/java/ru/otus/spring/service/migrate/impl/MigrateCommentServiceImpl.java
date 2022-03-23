package ru.otus.spring.service.migrate.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.jpa.Book;
import ru.otus.spring.model.jpa.Comment;
import ru.otus.spring.repository.mongo.BookRepository;
import ru.otus.spring.repository.mongo.CommentRepository;
import ru.otus.spring.service.migrate.MigrateCommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MigrateCommentServiceImpl implements MigrateCommentService {
    private final CommentRepository mongoCommentRepository;
    private final ru.otus.spring.repository.jpa.CommentRepository jpaCommentRepository;
    private final BookRepository bookRepository;


    @Override
    public void migrate() {
        List<Comment> commentList = jpaCommentRepository.findAll();
        mongoCommentRepository.saveAll(getMongoCommentList(commentList));
    }

    private List<ru.otus.spring.model.mongo.Comment> getMongoCommentList(List<Comment> commentList) {
        return commentList.stream()
                .map(this::getMongoComment)
                .collect(Collectors.toUnmodifiableList());
    }

    private ru.otus.spring.model.mongo.Comment getMongoComment(Comment comment) {
        Book book = comment.getBook();
        ru.otus.spring.model.mongo.Book mongoBook = bookRepository.findByName(book.getName());
        return new ru.otus.spring.model.mongo.Comment(mongoBook, comment.getMessage());
    }
}
