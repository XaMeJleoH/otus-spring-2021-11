package ru.otus.spring.service.impl;

import ru.otus.spring.service.CustomPrintService;

public class ConsoleCustomPrintServiceImpl implements CustomPrintService {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
