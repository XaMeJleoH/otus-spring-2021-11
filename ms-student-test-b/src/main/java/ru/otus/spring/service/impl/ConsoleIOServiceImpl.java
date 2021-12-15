package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.component.StreamConfigService;
import ru.otus.spring.service.IOService;

import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class ConsoleIOServiceImpl implements IOService {
    private final StreamConfigService streamConfigService;

    @Override
    public void print(String message) {
        streamConfigService.printStream().println(message);
    }

    @Override
    public void printFormat(String format, Object... args) {
        String message = new Formatter().format(format, args).toString();
        streamConfigService.printStream().println(message);
    }

    @Override
    public String get() {
        return streamConfigService.getScanner().nextLine();
    }

}
