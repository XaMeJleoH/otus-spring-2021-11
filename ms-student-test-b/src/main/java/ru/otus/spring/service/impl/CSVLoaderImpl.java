package ru.otus.spring.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.CSVLocale;
import ru.otus.spring.service.CSVLoader;

import java.util.Locale;
import java.util.Set;

@Service
public class CSVLoaderImpl implements CSVLoader {
    private final String csvFilePath;
    private final String csvRuFilePath;

    public CSVLoaderImpl(@Value("${test.file.csv}") String csvFilePath, @Value("${test.file.csv.ru}") String csvRuFilePath) {
        this.csvFilePath = csvFilePath;
        this.csvRuFilePath = csvRuFilePath;
    }

    @Override
    public Set<CSVLocale> load() {
        return Set.of(createCSVLocale("en", csvFilePath), createCSVLocale("ru", csvRuFilePath));
    }

    private CSVLocale createCSVLocale(String localeString, String csvFilePath) {
        return new CSVLocale(new Locale(localeString), csvFilePath);
    }

    @Override
    public String defineCSVClasspath(CSVLocale csvLocale) {
        return csvLocale.getClasspath();
    }
}
