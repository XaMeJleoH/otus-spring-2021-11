package ru.otus.spring.dao;

import ru.otus.spring.model.CSVLocale;

import java.util.Set;

public interface CSVLoader {
    Set<CSVLocale> load();

    String defineCSVClasspath(CSVLocale csvLocale);
}
