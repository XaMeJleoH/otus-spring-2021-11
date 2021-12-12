package ru.otus.spring.shell.event.publisher;

import ru.otus.spring.model.User;

public interface EventsPublisher {
    void publish(User user);
}
