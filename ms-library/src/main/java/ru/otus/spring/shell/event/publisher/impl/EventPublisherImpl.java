package ru.otus.spring.shell.event.publisher.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Message;
import ru.otus.spring.shell.event.publisher.EventsPublisher;
import ru.otus.spring.shell.event.publisher.LibraryEvent;

@Service
@RequiredArgsConstructor
public class EventPublisherImpl implements EventsPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(Message message) {
        publisher.publishEvent(new LibraryEvent(this, message));
    }
}
