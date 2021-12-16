package ru.otus.spring.shell.event.publisher;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.otus.spring.model.Message;

public class LibraryEvent extends ApplicationEvent {

    @Getter
    private final Message message;

    public LibraryEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }
}
