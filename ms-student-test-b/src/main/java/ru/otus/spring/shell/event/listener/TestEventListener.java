package ru.otus.spring.shell.event.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.model.Message;
import ru.otus.spring.model.User;
import ru.otus.spring.service.TestRunnerService;
import ru.otus.spring.shell.event.publisher.TestEvent;

@Component
@RequiredArgsConstructor
public class TestEventListener {
    private final TestRunnerService testRunnerService;

    @EventListener
    public void listener(TestEvent testEvent) {
        Message message = testEvent.getMessagese();
        testRunnerService.run(new User(message.getLastName(), message.getFirstName()));
    }
}
