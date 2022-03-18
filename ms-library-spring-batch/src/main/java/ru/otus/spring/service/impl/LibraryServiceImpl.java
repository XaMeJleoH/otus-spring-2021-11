package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.jpa.Author;
import ru.otus.spring.model.jpa.Book;
import ru.otus.spring.model.jpa.Comment;
import ru.otus.spring.model.jpa.Genre;
import ru.otus.spring.repository.jpa.AuthorRepository;
import ru.otus.spring.repository.jpa.BookRepository;
import ru.otus.spring.repository.jpa.CommentRepository;
import ru.otus.spring.repository.jpa.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final IOService ioService;

    @Override
    @Transactional
    public boolean publicBook(String name, long authorId, long[] genres) throws LibraryException {
        Author author = getAuthor(authorId);
        List<Genre> genreList = getGenres(genres);
        Book libraryBook = new Book(name, List.of(author), genreList);

        Book book = bookRepository.save(libraryBook);
        ioService.print(book.toString());
        return true;
    }

    private List<Genre> getGenres(long[] genres) {
        List<Genre> genreList = new ArrayList<>();
        Arrays.stream(genres).forEach(genreId -> genreRepository.findById(genreId).ifPresent(genreList::add));
        return genreList;
    }

    private Author getAuthor(long authorId) throws LibraryException {
        return authorRepository.findById(authorId).orElseThrow(() -> new LibraryException("Не удалось получить автора"));
    }

    @Override
    public boolean addComment(long bookId, String comment) throws LibraryException {
        Book book = getBook(bookId);
        commentRepository.save(new Comment(comment, book));
        return false;
    }

    private Book getBook(long bookId) throws LibraryException {
        return bookRepository.findById(bookId).orElseThrow(() -> new LibraryException("Не удалось получить книгу"));
    }

    @Override
    @Transactional(readOnly = true)
    public void showAllBook() {
        ioService.print(bookRepository.findAll().toString());
    }

    @Override
    @Transactional
    public boolean createAuthor(Author author) {
        authorRepository.save(author);
        ioService.print(author.toString());
        return true;
    }

    @Override
    public void showAllAuthor() {
        ioService.print(authorRepository.findAll().toString());
    }

    @Override
    @Transactional
    public boolean createGenre(Genre genre) {
        genreRepository.save(genre);
        ioService.print(genre.toString());
        return true;
    }

    @Override
    public void showAllGenre() {
        ioService.print(genreRepository.findAll().toString());
    }
}
