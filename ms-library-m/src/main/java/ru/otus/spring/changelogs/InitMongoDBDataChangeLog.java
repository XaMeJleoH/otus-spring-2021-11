package ru.otus.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.repository.GenreRepository;

import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author authorDontzova;
    private Author authorVasya;
    private Genre genreStory;
    private Genre genreFantasy;
    private Genre genreDetective;
    private Book bookHarry;
    private Book bookSpring;

    @ChangeSet(order = "000", id = "dropDB", author = "MakhanovAA", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "MakhanovAA", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        authorDontzova = repository.save(new Author("Dontzova"));
        authorVasya = repository.save(new Author("Vasya"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "MakhanovAA", runAlways = true)
    public void initGenres(GenreRepository repository) {
        genreStory = repository.save(new Genre("Story"));
        genreFantasy = repository.save(new Genre("Fantasy"));
        genreDetective = repository.save(new Genre("Detective"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "MakhanovAA", runAlways = true)
    public void initBooks(BookRepository repository) {
        bookHarry = new Book("Harry", List.of(authorDontzova), List.of(genreStory, genreFantasy));
        repository.save(bookHarry);

        bookSpring = new Book("Spring", List.of(authorVasya), List.of(genreDetective));
        repository.save(bookSpring);
    }

    @ChangeSet(order = "004", id = "initComments", author = "MakhanovAA", runAlways = true)
    public void initComments(CommentRepository repository) {
        repository.save(new Comment(bookHarry, "AMAZING BOOK"));
        repository.save(new Comment(bookHarry, "Ron is best!"));
        repository.save(new Comment(bookHarry, "Pff..."));

        repository.save(new Comment(bookSpring, "True story"));
        repository.save(new Comment(bookSpring, "Not bad"));
        repository.save(new Comment(bookSpring, "W.f"));
        repository.save(new Comment(bookSpring, "Terrible book and author"));
    }
}
