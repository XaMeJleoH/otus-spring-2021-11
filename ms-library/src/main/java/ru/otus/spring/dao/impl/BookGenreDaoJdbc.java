package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.domain.BookGenre;

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

    @Override
    public void insert(BookGenre bookGenre) {
        namedParameterJdbcOperations.update("insert into book_genre (book_id, genre_id) values (:bookId, :genreId)",
                Map.of("bookId", bookGenre.getBookId(), "genreId", bookGenre.getGenreId()));
    }

    @Override
    public List<BookGenre> getByGenreId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from book_genre where genre_id = :id", params, new BookGenreMapper()
        );
    }

    @Override
    public List<BookGenre> getByBookId(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.query(
                "select book_id, genre_id from book_genre where book_id = :id", params, new BookGenreMapper()
        );
    }

    @Override
    public List<BookGenre> getAll() {
        return jdbc.query("select book_id, genre_id from book_genre", new BookGenreMapper());
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

    private static class BookGenreMapper implements RowMapper<BookGenre> {

        @Override
        public BookGenre mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("book_id");
            long genreId = resultSet.getLong("genre_id");
            return new BookGenre(bookId, genreId);
        }
    }
}
