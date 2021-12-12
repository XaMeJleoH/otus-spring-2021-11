package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.configuration.CSVLoaderConfig;
import ru.otus.spring.model.CSVLocale;
import ru.otus.spring.service.CSVLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CSVLoaderImpl implements CSVLoader {
    private final CSVLoaderConfig csvLoaderConfig;

    @Override
    public Set<CSVLocale> load() {

        List<CSVLoaderConfig.CSVFileConfig> csvFileConfigList = csvLoaderConfig.getCsvFileConfigList();
        return new HashSet<>();
    }

    private CSVLocale createCSVLocale(String localeString, String csvFilePath) {
        return new CSVLocale(new Locale(localeString), csvFilePath);
    }

    @Override
    public String defineCSVClasspath(CSVLocale csvLocale) {
        return csvLocale.getClasspath();
    }
}
