package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CSVLocale {
    private Locale locale;
    private String classpath;
}
