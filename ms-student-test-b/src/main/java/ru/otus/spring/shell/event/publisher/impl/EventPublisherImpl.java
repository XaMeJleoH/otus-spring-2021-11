package ru.otus.spring.shell.event.publisher.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.User;
import ru.otus.spring.shell.event.publisher.EventsPublisher;
import ru.otus.spring.shell.event.publisher.TestEvent;

@Service
@RequiredArgsConstructor
public class EventPublisherImpl implements EventsPublisher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(User user) {
        publisher.publishEvent(new TestEvent(this, user));
    }
}
