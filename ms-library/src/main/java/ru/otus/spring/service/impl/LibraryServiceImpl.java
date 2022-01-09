package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LibraryService;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookDao bookDao;
    private final IOService ioService;

    @Override
    public boolean publicBook(Book libraryBook) {
        bookDao.insert(libraryBook);
        return true;
    }

    @Override
    public void showAllBook() {
        ioService.print(bookDao.getAll().toString());
    }
}
