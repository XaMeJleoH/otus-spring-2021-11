package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookGenreDaoJdbc implements BookGenreDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final GenreDao genreDao;

    @Override
    public void insert(BookGenre bookGenre) {
        namedParameterJdbcOperations.update("insert into book_genre (book_id, genre_id) values (:bookId, :genreId)",
                Map.of("bookId", bookGenre.getBook().getId(), "genreId", bookGenre.getGenre().getId()));
    }

    @Override
    public List<BookGenre> getByGenreId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from book_genre where genre_id = :id", params, new BookGenreMapper(genreDao)
        );
    }

    @Override
    public List<BookGenre> getByBookId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from book_genre where book_id = :id", params, new BookGenreMapper(genreDao)
        );
    }

    @Override
    public List<BookGenre> getAll() {
        return jdbc.query("select book_id, genre_id from book_genre", new BookGenreMapper(genreDao));
    }

    @Override
    public void deleteByGenreId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book_genre where genre_id = :id", params
        );
    }

    @Override
    public void deleteByBookId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book_genre where book_id = :id", params
        );
    }

    @RequiredArgsConstructor
    private static class BookGenreMapper implements RowMapper<BookGenre> {
        private final GenreDao genreDao;

        @Override
        public BookGenre mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("book_id");
            long genreId = resultSet.getLong("genre_id");
            Book book = new Book(bookId);
            Genre genre = genreDao.getById(genreId);
            return new BookGenre(book, genre);
        }
    }
}
