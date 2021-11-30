package ru.otus.spring.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.model.QuestionsReadingException;
import ru.otus.spring.model.StudentTestException;

import static org.junit.jupiter.api.Assertions.*;

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