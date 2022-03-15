package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.service.LibraryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    private Book getBook(String bookId) throws LibraryException {
        return bookRepository.findById(bookId).orElseThrow(() -> new LibraryException("Не удалось получить книгу"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBook(String id) throws LibraryException {
        return getBook(id);
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public boolean updateBookName(String bookId, String name) throws LibraryException {
        Book book = getBook(bookId);
        List<Comment> commentList = commentRepository.getAllByBook(book);
        book.setName(name);
        commentList.forEach(comment -> {
            comment.setBook(book);
            commentRepository.save(comment);
        });
        bookRepository.save(book);
        return true;
    }

    @Override
    @Transactional
    public void deleteBook(String bookId) throws LibraryException {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        commentRepository.deleteAllByBook(book);
    }
}
