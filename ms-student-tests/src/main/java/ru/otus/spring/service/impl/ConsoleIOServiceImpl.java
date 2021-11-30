package ru.otus.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.service.IOService;

import java.util.Scanner;

@Service
public class ConsoleIOServiceImpl implements IOService {
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String get() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
