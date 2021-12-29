package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;

import java.util.Formatter;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleIOServiceImpl implements IOService {
    private final Scanner scannerIn = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public void printFormat(String format, Object... args) {
        String message = new Formatter().format(format, args).toString();
        System.out.println(message);
    }

}
