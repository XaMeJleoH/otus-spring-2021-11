package ru.otus.spring.shell.event.publisher;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.otus.spring.model.User;

public class TestEvent extends ApplicationEvent {

    @Getter
    private final User user;

    public TestEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
