package ru.otus.spring.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.AuthorDTO;
import ru.otus.spring.dto.BookDTO;
import ru.otus.spring.dto.GenreDTO;
import ru.otus.spring.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final BookGenreDao bookGenreDao;
    private final GenreDao genreDao;


    @Override
    public BookDTO getBookById(long id) {
        Book book = bookDao.getById(id);
        AuthorDTO authorDTO = getAuthorDTO(book.getAuthorId());
        List<GenreDTO> genreDTOList = getGenreDTOList(book.getId());
        return new BookDTO(book.getId(), book.getName(), authorDTO, genreDTOList);
    }

    private List<GenreDTO> getGenreDTOList(long bookId) {
        List<BookGenre> bookGenreList = bookGenreDao.getByBookId(bookId);
        return getGenreDTOList(bookGenreList);
    }

    private AuthorDTO getAuthorDTO(long authorId) {
        Author author = authorDao.getById(authorId);
        return new AuthorDTO(author.getId(), author.getName());
    }

    private List<GenreDTO> getGenreDTOList(List<BookGenre> bookGenreList) {
        List<GenreDTO> genreDTOList = new ArrayList<>();
        bookGenreList.forEach(bookGenre -> {
            Genre genre = genreDao.getById(bookGenre.getGenreId());
            genreDTOList.add(new GenreDTO(genre.getId(), genre.getName()));
        });
        return genreDTOList;
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> bookList = bookDao.getAll();
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookList.forEach(book -> {
            AuthorDTO authorDTO = getAuthorDTO(book.getAuthorId());
            List<GenreDTO> genreDTOList = getGenreDTOList(book.getId());
            bookDTOList.add(new BookDTO(book.getId(), book.getName(), authorDTO, genreDTOList));
        });
        return bookDTOList;
    }

    @Override
    public void insertBook(BookDTO bookDTO) {
        AuthorDTO authorDTO = bookDTO.getAuthor();
        List<GenreDTO> genreDtoList = bookDTO.getGenreList();
        long authorId = authorDao.insert(new Author(authorDTO.getName()));
        long bookId = bookDao.insert(new Book(bookDTO.getName(), authorId));

        List<BookGenre> bookGenreList = new ArrayList<>();
        genreDtoList.forEach(genreDTO -> {
            Genre genre = genreDao.getByName(genreDTO.getName());
            if (genre == null) {
                long genreId = genreDao.insert(new Genre(genreDTO.getName()));
                bookGenreList.add(new BookGenre(bookId, genreId));
            } else {
                bookGenreList.add(new BookGenre(bookId, genre.getId()));
            }
        });
        bookGenreList.forEach(bookGenreDao::insert);

    }

    @Override
    public void deleteBook(BookDTO bookDTO) {

    }
}
