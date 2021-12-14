package ru.otus.spring.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.otus.spring.component.LocaleProvider;

import java.util.List;
import java.util.Locale;

@ConfigurationProperties(prefix = "test.file")
@Component
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CSVLoaderConfig {
    private List<CSVFileConfig> csvFileConfigList;
    private final LocaleProvider localeProvider;

    public String getCsvPath() {
        List<CSVLoaderConfig.CSVFileConfig> csvFileConfigList = this.getCsvFileConfigList();
        return csvFileConfigList.stream().filter(csvFileConfig ->
                Locale.forLanguageTag(csvFileConfig.getLocale()).equals(localeProvider.getLocale()))
                .findAny()
                .map(CSVLoaderConfig.CSVFileConfig::getCsvFile)
                .orElseThrow();
    }

    @Getter
    @Setter
    @ToString
    public static class CSVFileConfig {
        private String locale;
        private String csvFile;
    }
}
