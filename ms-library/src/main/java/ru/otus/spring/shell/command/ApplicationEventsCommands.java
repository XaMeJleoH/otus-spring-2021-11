package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.Message;
import ru.otus.spring.shell.event.publisher.EventsPublisher;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final EventsPublisher eventsPublisher;

    private final List<String> genreNameList = new ArrayList<>();
    private Message message = new Message(genreNameList);

    @ShellMethod(value = "Set Author name", key = {"a", "author"})
    public void setAuthorName(@ShellOption String authorName) {
        this.message.setAuthorName(authorName);
    }

    @ShellMethod(value = "Set Book name", key = {"b", "book"})
    public void setBookName(@ShellOption String bookName) {
        this.message.setBookName(bookName);
    }

    @ShellMethod(value = "Add Genre to Book", key = {"g", "genre"})
    public void addGenreToBook(@ShellOption String genreName) {
        this.genreNameList.add(genreName);
    }

    @ShellMethod(value = "Reset fills Book", key = {"r", "resetBook"})
    public void resetFillBook() {
        this.message = new Message(new ArrayList<>());
    }

    @ShellMethod(value = "Add Book to Library", key = {"p", "public"})
    @ShellMethodAvailability(value = "isPossibleStartTest")
    public String publishEvent() {
        eventsPublisher.publish(message);
        return "Book was added to Library";
    }
}
