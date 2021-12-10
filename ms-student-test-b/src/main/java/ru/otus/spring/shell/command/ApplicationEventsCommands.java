package ru.otus.spring.shell.command;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.model.User;
import ru.otus.spring.service.LocaleService;
import ru.otus.spring.shell.event.impl.EventPublisherImpl;

import java.util.Locale;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {
    private final EventPublisherImpl eventsPublisher;
    private final LocaleService localeService;

    private final User user = new User();

    @ShellMethod(value = "Set locale for test", key = {"locale", "language"})
    public String locale(@ShellOption(defaultValue = "ru") String localeString) {
        this.user.setLocale(Locale.forLanguageTag(localeString));
        return localeService.getLocaleMessage("test.hello", Locale.forLanguageTag("ru"));
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
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public String publishEvent() {
        eventsPublisher.publish();
        return localeService.getLocaleMessage("test.started", user.getLocale());
    }

    private Availability isPublishEventCommandAvailable() {
        if (user.getLocale() == null || StringUtils.isAnyBlank(user.getLastName(),user.getFirstName())) {
            return Availability.unavailable("Fill the data: locale, lastName and firstName");
        }
        return Availability.available();
    }
}
