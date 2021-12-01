package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.model.QuestionsReadingException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileLoaderImplTest {

    private FileLoaderImpl fileLoader;

    @BeforeEach
    void setUp() {
        fileLoader = new FileLoaderImpl();
    }

    @Test
    @DisplayName("Получение файла")
    void loadFile() {
        assertThrows(QuestionsReadingException.class, () -> fileLoader.loadFile("gdf"));
    }
}