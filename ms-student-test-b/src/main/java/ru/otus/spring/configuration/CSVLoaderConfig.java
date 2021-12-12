package ru.otus.spring.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "test.file")
@Component
@Getter
@Setter
@ToString
public class CSVLoaderConfig {
    private List<CSVFileConfig> csvFileConfigList;

    @Getter
    @Setter
    @ToString
    public static class CSVFileConfig {
        private String locale;
        private String csvFile;
    }
}
