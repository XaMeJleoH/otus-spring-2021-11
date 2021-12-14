package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.component.LocaleProvider;
import ru.otus.spring.model.User;
import ru.otus.spring.service.IOService;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.shell.event.publisher.EventsPublisher;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final EventsPublisher eventsPublisher;
    private final LocaleService localeService;
    private final LocaleProvider localeProvider;
    private final IOService ioService;

    private final User user = new User();

    @ShellMethod(value = "Set locale for test", key = {"locale", "language"})
    public void locale(@ShellOption(defaultValue = "en") String localeString) {
        localeProvider.setLocale(localeString);
    }

    @ShellMethod(value = "Set LastName", key = {"l", "lastName"})
    public void lastName(@ShellOption String lastName) {
        this.user.setLastName(lastName);
    }

    @ShellMethod(value = "Set FirstName", key = {"f", "firstName"})
    public void firstName(@ShellOption String firstName) {
        this.user.setFirstName(firstName);
    }

    @ShellMethod(value = "Start test", key = {"s", "start"})
    @ShellMethodAvailability(value = "isPossibleStartTest")
    public String publishEvent() {
        ioService.print("test.started");
        eventsPublisher.publish(user);
        return localeService.getLocaleMessage("test.finished");
    }

    private Availability isPossibleStartTest() {
        if (StringUtils.isAnyBlank(user.getLastName(), user.getFirstName())) {
            return Availability.unavailable("Fill the data: lastName and firstName");
        }
        return Availability.available();
    }
}
