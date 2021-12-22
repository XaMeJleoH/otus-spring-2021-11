package ru.otus.spring.shell.event.publisher;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.otus.spring.model.Message;

public class TestEvent extends ApplicationEvent {

    @Getter
    private final Message messagese;

    public TestEvent(Object source, Message messagese) {
        super(source);
        this.messagese = messagese;
    }
}
