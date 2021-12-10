package ru.otus.spring.shell.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.spring.shell.event.EventsPublisher;

@Service
@RequiredArgsConstructor
public class EventPublisherImpl implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish() {

    }
}
