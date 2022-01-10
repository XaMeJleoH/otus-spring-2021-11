package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LibraryService;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final IOService ioService;

    @Override
    public boolean publicBook(Book libraryBook) {
        bookRepository.save(libraryBook);
        return true;
    }

    @Override
    public void showAllBook() {
        ioService.print(bookRepository.findAll().toString());
    }
}
