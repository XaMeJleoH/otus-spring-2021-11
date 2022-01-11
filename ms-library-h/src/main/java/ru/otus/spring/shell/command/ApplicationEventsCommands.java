package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Book;
import ru.otus.spring.model.Comment;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final LibraryService libraryService;

    private final List<Genre> genreNameList = new ArrayList<>();
    private final List<Comment> commentList = new ArrayList<>();
    private Book libraryBook = new Book(genreNameList);

    @ShellMethod(value = "Set Author name", key = {"a", "author"})
    public void setAuthorName(@ShellOption String authorName) {
        this.libraryBook.setAuthor(new Author(0L, authorName));
    }

    @ShellMethod(value = "Set Book name", key = {"b", "book"})
    public void setBookName(@ShellOption String bookName) {
        this.libraryBook.setName(bookName);
        this.libraryBook.setCommentList(Collections.emptyList());
    }

    @ShellMethod(value = "Add Genre to Book", key = {"g", "genre"})
    public void addGenreToBook(@ShellOption String genreName) {
        this.genreNameList.add(new Genre(genreName));
    }

    @ShellMethod(value = "Add Comment to Book", key = {"c", "comment"})
    public void addCommentToBook(@ShellOption String comment) {
        this.commentList.add(new Comment(comment));
    }

    @ShellMethod(value = "Reset fills Book", key = {"r", "resetBook"})
    public void resetFillBook() {
        this.libraryBook = new Book(new ArrayList<>(), new ArrayList<>());
    }

    @ShellMethod(value = "Add Book to Library", key = {"p", "public"})
    public String publishEvent() {
        libraryService.publicBook(libraryBook);
        return "Book was added to Library";
    }

    @ShellMethod(value = "Show All Book in Library", key = {"s", "show"})
    public void showAllBook() {
        libraryService.showAllBook();
    }
}
