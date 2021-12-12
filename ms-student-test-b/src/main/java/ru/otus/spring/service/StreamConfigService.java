package ru.otus.spring.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class StreamConfigService {

    public InputStream inputStream() {
        return new BufferedInputStream(System.in);
    }

    public PrintStream printStream() {
        return new PrintStream(System.out);
    }

    @Getter
    private final Scanner scanner = new Scanner(this.inputStream());
}
