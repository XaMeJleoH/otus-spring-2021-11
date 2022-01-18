package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.exception.LibraryException;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.service.LibraryService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationLibraryCommands {
    private final LibraryService libraryService;

    @ShellMethod(value = "Show All Author in Library", key = {"authors"})
    public void showAllAuthor() {
        libraryService.showAllAuthor();
    }

    @ShellMethod(value = "Show All Genre in Library", key = {"genres"})
    public void showAllGenre() {
        libraryService.showAllGenre();
    }

    @ShellMethod(value = "Create Author", key = {"a", "author"})
    public void createAuthor(@ShellOption String authorName) {
        libraryService.createAuthor(new Author(authorName));
    }

    @ShellMethod(value = "Create Genre", key = {"g", "genre"})
    public void createGenre(@ShellOption String genre) {
        libraryService.createGenre(new Genre(genre));
    }

    @ShellMethod(value = "Add Book to Library", key = {"p", "public"})
    public String publishBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String[] genres)
            throws LibraryException {
        libraryService.publicBook(name, authorId, genres);
        return "Book was added to Library";
    }

    @ShellMethod(value = "Add Comment to Book", key = {"c", "comment"})
    public String addCommentToBook(@ShellOption String bookId, String comment) throws LibraryException {
        libraryService.addComment(bookId, comment);
        return "Comment was added to Book";
    }


    @ShellMethod(value = "Show All Book in Library", key = {"s", "show"})
    public void showAllBook() {
        libraryService.showAllBook();
    }

    @ShellMethod(value = "Show All Comments Book in Library", key = {"comments"})
    public void showAllCommentsBook(@ShellOption String bookId) throws LibraryException {
        libraryService.showAllCommentsBook(bookId);
    }
}
