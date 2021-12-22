package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.AuthorDTO;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.dto.GenreDTO;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;

    @Override
    public boolean publicBook(LibraryBook libraryBook) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(new AuthorDTO(libraryBook.getAuthor().getName()));
        bookDTO.setName(libraryBook.getName());
        List<GenreDTO> genreDTOList = new ArrayList<>();
        libraryBook.getGenreList().forEach(genre -> genreDTOList.add(new GenreDTO(genre.getName())));
        bookDTO.setGenreList(genreDTOList);
        bookRepository.insertBook(bookDTO);
        return true;
    }
}
