package ru.otus.spring.component;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
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
