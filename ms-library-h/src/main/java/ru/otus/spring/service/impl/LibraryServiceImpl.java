package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LibraryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public boolean publicBook(String name, long authorId, long[] genres) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        List<Genre> genreList = new ArrayList<>();
        Arrays.stream(genres).forEach(genreId -> {
            Optional<Genre> genre = genreRepository.findById(genreId);
            genreList.add(genre.get());
        });
        Book libraryBook = new Book(name, List.of(authorOptional.get()), genreList);

        bookRepository.save(libraryBook);
        ioService.print(libraryBook.toString());
        return true;
    }

    @Override
    public boolean addComment(long bookId, String comment) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        commentRepository.save(new Comment(comment, bookOptional.get()));
        return false;
    }

    @Override
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
