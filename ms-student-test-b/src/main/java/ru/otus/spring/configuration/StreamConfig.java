package ru.otus.spring.configuration;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class StreamConfig {

    public InputStream inputStream() {
        return new BufferedInputStream(System.in);
    }

    public PrintStream printStream() {
        return new PrintStream(System.out);
    }
}
