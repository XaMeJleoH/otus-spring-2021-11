package ru.otus.spring.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.BookGenreDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    @Autowired
    private BookGenreDao bookGenreDao;

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
        List<BookGenre> bookGenreList = new ArrayList<>();
        book.getGenreList().forEach(genreDTO -> {
            Genre genre = genreDao.getByName(genreDTO.getName());
            if (genre == null) {
                Genre genreNew = new Genre();
                genreNew.setName(genreDTO.getName());
                long genreId = genreDao.insert(genreNew);
                genreNew.setId(genreId);
                bookGenreList.add(new BookGenre(book, genreNew));
            } else {
                bookGenreList.add(new BookGenre(book, genre));
            }
        });
        bookGenreList.forEach(bookGenreDao::insert);
        return bookId;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, author_id from book where id = :id", params, new BookMapper(authorDao, bookGenreDao)
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, author_id from book", new BookMapper(authorDao, bookGenreDao));
    }

    @Override
    public void deleteById(long id) {
        bookGenreDao.deleteByBookId(id);
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    @RequiredArgsConstructor
    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final BookGenreDao bookGenreDao;

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            Author author = authorDao.getById(authorId);
            List<BookGenre> bookGenreList = bookGenreDao.getByBookId(id);
            List<Genre> genreList = getGenreList(bookGenreList);
            return new Book(id, name, author, genreList);
        }

        private List<Genre> getGenreList(List<BookGenre> bookGenreList) {
            List<Genre> genreList = new ArrayList<>();
            bookGenreList.forEach(bookGenre -> genreList.add(bookGenre.getGenre()));
            return genreList;
        }
    }
}
