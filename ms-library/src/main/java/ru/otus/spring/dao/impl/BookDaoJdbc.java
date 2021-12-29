package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public long insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long authorId = authorDao.insert(book.getAuthor());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(Map.of("name", book.getName(),
                "authorId", authorId));
        namedParameterJdbcOperations.update("insert into book (name, author_id) values (:name, :authorId)",
                mapSqlParameterSource, keyHolder, new String[]{"ID"});
        long bookId = (long) keyHolder.getKey();
        book.setId(bookId);
        insertGenreLink(bookId, book.getGenreList().stream().map(Genre::getName).collect(Collectors.toUnmodifiableList()));
        return bookId;
    }

    @Override
    public void insertGenreLink(long bookId, List<String> genreList) {
        genreList.forEach(genreName -> {
            Genre genre = genreDao.getByName(genreName);
            if (genre == null) {
                Genre genreNew = new Genre();
                genreNew.setName(genreName);
                long genreId = genreDao.insert(genreNew);
                genreNew.setId(genreId);
                insertBookGenre(bookId, genreId);
            } else {
                insertBookGenre(bookId, genre.getId());
            }
        });
    }

    private void insertBookGenre(long bookId, long genreId) {
        namedParameterJdbcOperations.update("insert into book_genre (book_id, genre_id) values (:bookId, :genreId)",
                Map.of("bookId", bookId, "genreId", genreId));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, b.author_id, a.name as author_name from book b " +
                        "join author a on a.id = b.author_id " +
                        "where b.id = :id", params, new BookMapper(genreDao)
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.name, b.author_id, a.name as author_name from book b " +
                "join author a on a.id = b.author_id ", new BookMapper(genreDao));
    }

    @Override
    public void deleteById(long id) {
        deleteGenreLink(id);
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    private void deleteGenreLink(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book_genre where book_id = :id", params
        );
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        private final GenreDao genreDao;

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            String authorName = resultSet.getString("author_name");
            Author author = Author.builder().id(authorId).name(authorName).build();
            List<Genre> genreList = genreDao.getByBookId(id);
            return new Book(id, name, author, genreList);
        }
    }
}
