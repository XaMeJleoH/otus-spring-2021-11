package ru.otus.spring.shell.event.publisher;

import ru.otus.spring.model.Message;

public interface EventsPublisher {
    void publish(Message message);
}
