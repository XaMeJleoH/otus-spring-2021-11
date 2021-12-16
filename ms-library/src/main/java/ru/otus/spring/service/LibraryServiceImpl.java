package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.AuthorDTO;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.dto.GenreDTO;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;

    @Override
    public boolean publicBook(LibraryBook libraryBook) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(new AuthorDTO(libraryBook.getAuthorName()));
        bookDTO.setName(libraryBook.getBookName());
        List<GenreDTO> genreDTOList = new ArrayList<>();
        libraryBook.getGenreNameList().forEach(genreName -> genreDTOList.add(new GenreDTO(genreName)));
        bookDTO.setGenreList(genreDTOList);
        bookRepository.insertBook(bookDTO);
        return true;
    }
}
