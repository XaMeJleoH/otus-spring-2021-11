package ru.otus.spring.shell.event.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.model.Author;
import ru.otus.spring.model.Genre;
import ru.otus.spring.model.LibraryBook;
import ru.otus.spring.model.Message;
import ru.otus.spring.service.LibraryService;
import ru.otus.spring.shell.event.publisher.LibraryEvent;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LibraryEventListener {
    private final LibraryService libraryService;

    @EventListener
    public void listener(LibraryEvent libraryEvent) {
        Message message = libraryEvent.getMessage();
        LibraryBook libraryBook = new LibraryBook();
        libraryBook.setAuthor(new Author(message.getAuthorName()));
        libraryBook.setName(message.getBookName());
        libraryBook.setGenreList(message.getGenreNameList().stream().map(this::getGenre).collect(Collectors.toList()));
        libraryService.publicBook(libraryBook);
    }

    private Genre getGenre(String genreName) {
        return new Genre(genreName);
    }
}
