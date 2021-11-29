package ru.otus.spring.service.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.service.CustomPrintService;

@Component
public class ConsoleCustomPrintServiceImpl implements CustomPrintService {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
