package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final LibraryService libraryService;

    private final List<Genre> genreNameList = new ArrayList<>();
    private LibraryBook libraryBook = new LibraryBook(genreNameList);

    @ShellMethod(value = "Set Author name", key = {"a", "author"})
    public void setAuthorName(@ShellOption String authorName) {
        this.libraryBook.setAuthor(new Author(authorName));
    }

    @ShellMethod(value = "Set Book name", key = {"b", "book"})
    public void setBookName(@ShellOption String bookName) {
        this.libraryBook.setName(bookName);
    }

    @ShellMethod(value = "Add Genre to Book", key = {"g", "genre"})
    public void addGenreToBook(@ShellOption String genreName) {
        this.genreNameList.add(new Genre(genreName));
    }

    @ShellMethod(value = "Reset fills Book", key = {"r", "resetBook"})
    public void resetFillBook() {
        this.libraryBook = new LibraryBook(new ArrayList<>());
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
