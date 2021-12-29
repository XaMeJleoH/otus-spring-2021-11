package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookDao bookDao;
    private final IOService ioService;

    @Override
    public boolean publicBook(LibraryBook libraryBook) {
        Book book = new Book();
        book.setAuthor(new Author(libraryBook.getAuthor().getName()));
        book.setName(libraryBook.getName());
        book.setGenreList(getGenreList(libraryBook));
        bookDao.insert(book);
        return true;
    }

    private List<Genre> getGenreList(LibraryBook libraryBook) {
        List<Genre> genreList = new ArrayList<>();
        libraryBook.getGenreList().forEach(genre -> genreList.add(new Genre(genre.getName())));
        return genreList;
    }

    @Override
    public void showAllBook() {
        ioService.print(bookDao.getAll().toString());
    }
}
