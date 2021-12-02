package ru.otus.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Scanner;

@Configuration
public class StreamConfig {

    @Bean
    public InputStream inputStream(){
        return new BufferedInputStream(System.in);
    }

    @Bean
    public PrintStream printStream() {
        return new PrintStream(System.out);
    }
}
