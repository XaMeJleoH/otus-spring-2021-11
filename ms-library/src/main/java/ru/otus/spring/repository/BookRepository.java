package ru.otus.spring.repository;

import ru.otus.spring.dto.BookDTO;

import java.util.List;

public interface BookRepository {

    BookDTO getBookById(long id);

    List<BookDTO> getAllBook();

    void insertBook(BookDTO bookDTO);

    void deleteBook(BookDTO bookDTO);
}
